# Klasy — zwięzły opis

Skrót z `../uml/03-diagram-klas.puml`. W razie rozbieżności obowiązuje diagram. Nazwy w kodzie muszą być identyczne.
Kod: `../src/main/java/systemplikow/<pakiet>/<Klasa>.java` (pakiety: `model`, `wyjatki`, `persystencja`).

## Pakiet `model`

### `SystemPlikow` — fasada
Przyjmuje polecenia użytkownika, pilnuje sesji (zalogowany użytkownik, bieżący katalog) i deleguje: uprawnienia do
`KontrolaDostepu`, konta do `RejestrUzytkownikow`, zapis do klas z pakietu `persystencja`.
- Pola: `-korzen: Katalog`, `-biezacyKatalog: Katalog`, `-zalogowany: Uzytkownik`
- Zależności (przez konstruktor): `RejestrUzytkownikow`, `SystemPlikowZapis`, `UzytkownicyZapis`. Własna `KontrolaDostepu`.
- Konstruktor: `SystemPlikow(rejestr: RejestrUzytkownikow, zapisDrzewa: SystemPlikowZapis, zapisKont: UzytkownicyZapis)`
- Sesja: `zaloguj(login: String, haslo: String): Uzytkownik`, `wyloguj(): void`,
  `zainicjujAdministratora(login: String, haslo: String): Uzytkownik`
- Drzewo: `utworzPlik(nazwa: String, tresc: String): Plik`, `utworzKatalog(nazwa: String): Katalog`,
  `usun(sciezka: String): void`, `usunKatalogRekurencyjnie(sciezka: String): int`,
  `przenies(sciezkaZrodlowa: String, sciezkaDocelowa: String): void`,
  `zmienNazwe(sciezka: String, nowaNazwa: String): void`, `przejdzDo(sciezka: String): void`,
  `listuj(sciezka: String): List<WezelSystemuPlikow>`, `odczytajPlik(sciezka: String): String`,
  `zapiszPlik(sciezka: String, tresc: String): void`, `znajdzWezel(sciezka: String): WezelSystemuPlikow`
- Prawa: `zmienPrawa(sciezka: String, prawa: PrawaDostepu): void`,
  `zmienWlasciciela(sciezka: String, nowyWlascicielLogin: String): void`
- Konta: `utworzKonto(login: String, haslo: String, rola: RolaUzytkownika): Uzytkownik`,
  `zmienHaslo(login: String, noweHaslo: String): void`
- Zapis: `zapiszDoPliku(): void`, `wczytajZPliku(): void`
- Prywatna: `-wymagajZalogowanego(): Uzytkownik`
- Wyjątki: `BladLogowaniaException` (zaloguj), `BrakUprawnienException`, `BladOperacjiException` (reszta)

### `WezelSystemuPlikow` — klasa abstrakcyjna (Composite)
Wspólna część pliku i katalogu: nazwa, rodzic, właściciel, prawa.
- Pole: `-nazwa: String`
- Relacje: `rodzic` (0..1 `Katalog`), `wlasciciel` (1 `Uzytkownik`), `PrawaDostepu` (kompozycja, 1)
- Metody: `getNazwa(): String`, `ustawNazwe(nowaNazwa: String): void`, `getSciezka(): String`,
  `getRodzic(): Katalog`, `setRodzic(rodzic: Katalog): void`, `getWlasciciel(): Uzytkownik`,
  `setWlasciciel(wlasciciel: Uzytkownik): void`, `getPrawa(): PrawaDostepu`, `setPrawa(prawa: PrawaDostepu): void`,
  `czyLezyWPoddrzewie(korzenPoddrzewa: WezelSystemuPlikow): boolean` (true, gdy węzeł to korzeń poddrzewa lub leży w nim)
- Abstrakcyjne: `czyKatalog(): boolean`, `getRozmiar(): int`

### `Plik` : `WezelSystemuPlikow`
- Pole: `-tresc: String`. Konstruktor: `Plik(nazwa: String, wlasciciel: Uzytkownik, tresc: String)`
- Metody: `odczytaj(): String`, `zapisz(tresc: String): void`, `czyKatalog(): boolean`, `getRozmiar(): int`

### `Katalog` : `WezelSystemuPlikow`
- Pole: `-dzieci: List<WezelSystemuPlikow>` (kompozycja, `*`)
- Metody: `dodajDziecko(wezel)` (ustawia też rodzica), `usunDziecko(wezel)` (zeruje rodzica),
  `znajdzDziecko(nazwa: String): WezelSystemuPlikow` (null gdy brak), `getDzieci(): List<WezelSystemuPlikow>`,
  `czyPusty(): boolean`, `policzPotomkow(): int`, `czyKatalog(): boolean`, `getRozmiar(): int`

### `PrawaDostepu`
- Pola: `-prawaWlasciciela: Set<Uprawnienie>`, `-prawaPozostalych: Set<Uprawnienie>`
- Konstruktor: `PrawaDostepu()` (domyślne prawa: pytanie otwarte, zob. `DECYZJE.md`)
- Metody: `czyPozwala(uprawnienie: Uprawnienie, jestWlascicielem: boolean): boolean`,
  `getPrawaWlasciciela()`, `getPrawaPozostalych()`, `toString(): String`

### `Uprawnienie` — enum: `ODCZYT`, `ZAPIS`, `WYKONANIE`

### `Uzytkownik`
- Pola: `-login: String` (niezmienny), `-hashHasla: String`, `-rola: RolaUzytkownika`
- Konstruktor: `Uzytkownik(login: String, haslo: String, rola: RolaUzytkownika)` (od razu haszuje hasło BCrypt)
- Metody: `sprawdzHaslo(proba: String): boolean`, `ustawHaslo(noweHaslo: String): void`,
  `czyAdministrator(): boolean`, `getLogin(): String`, `getRola(): RolaUzytkownika`

### `RolaUzytkownika` — enum: `UZYTKOWNIK`, `ADMINISTRATOR`

### `RejestrUzytkownikow`
Zbiór kont (kompozycja `*` `Uzytkownik`), klucz: login.
- Pole: `-uzytkownicy: Map<String, Uzytkownik>`
- Metody: `dodaj(uzytkownik: Uzytkownik): void`, `znajdz(login: String): Uzytkownik` (null gdy brak),
  `czyIstnieje(login: String): boolean`, `czyPusty(): boolean`, `getUzytkownicy(): Collection<Uzytkownik>`

### `KontrolaDostepu` — bezstanowa
Jedyne miejsce z regułami uprawnień. Metody `wymagaj*` rzucają `BrakUprawnienException`.
- `czyMaDostep(u: Uzytkownik, w: WezelSystemuPlikow, p: Uprawnienie): boolean`: administrator zawsze true,
  inaczej prawa właściciela lub pozostałych (zależnie od `w.getWlasciciel() == u`)
- `wymagaj(u, w, p): void`
- `wymagajWlascicielaLubAdministratora(u: Uzytkownik, w: WezelSystemuPlikow): void`
- `wymagajAdministratora(u: Uzytkownik): void`

## Pakiet `wyjatki`
`SystemPlikowException` ← `BrakUprawnienException`, `BladLogowaniaException`, `BladOperacjiException`.

## Pakiet `persystencja`

### `SystemPlikowZapis`
- Pole: `-plik: Path`
- `zapisz(korzen: Katalog): void` (przez `WezelDane.zWezla` i Gson, błąd I/O → `BladOperacjiException`)
- `wczytaj(rejestr: RejestrUzytkownikow): Katalog` (zwraca nowe drzewo, nie modyfikuje istniejącego)

### `WezelDane` — płaski format zapisu
- Pola publiczne: `rodzaj: RodzajWezla`, `nazwa`, `wlascicielLogin: String`, `prawaWlasciciela`, `prawaPozostalych`,
  `tresc` (tylko plik), `dzieci: List<WezelDane>` (tylko katalog)
- `static zWezla(wezel: WezelSystemuPlikow): WezelDane` (rekurencyjnie)
- `doWezla(rejestr: RejestrUzytkownikow): WezelSystemuPlikow` (rekurencyjnie, nieznany właściciel → `BladOperacjiException`)

### `RodzajWezla` — enum: `PLIK`, `KATALOG`

### `UzytkownicyZapis`
- Pole: `-plik: Path`
- `zapisz(rejestr: RejestrUzytkownikow): void`, `wczytaj(): RejestrUzytkownikow`
