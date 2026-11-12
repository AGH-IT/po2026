# Powiązania: przypadek użycia → sekwencja → metoda → klasy

Pliki sekwencji są w `../uml/`. Kod klas: `../src/main/java/systemplikow/<pakiet>/<Klasa>.java`. „Aktor”: U = Użytkownik, A = Administrator (A dziedziczy wszystko po U).

## Przypadki użycia

| Przypadek użycia (`01`) | Aktor | Sekwencja | Metoda wejściowa | Klasy biorące udział |
|---|---|---|---|---|
| Zaloguj się | U | `02-sekwencja-zaloguj-sie.puml` | `SystemPlikow.zaloguj` | `SystemPlikow`, `RejestrUzytkownikow`, `Uzytkownik`, BCrypt |
| Odrzuć logowanie (`extend` Zaloguj) | U | gałąź `alt` w sekwencji „zaloguj-sie” | `BladLogowaniaException` | `SystemPlikow` |
| Wyloguj się | U | brak (prosta) | `SystemPlikow.wyloguj` | `SystemPlikow` |
| Sprawdź prawa dostępu (`include`) | (wewnętrzny) | `02-sekwencja-sprawdz-prawa-dostepu.puml` | `KontrolaDostepu.wymagaj` | `KontrolaDostepu`, `Uzytkownik`, `WezelSystemuPlikow`, `PrawaDostepu` |
| Zgłoś brak uprawnień (`extend` Sprawdź) | (wewnętrzny) | gałąź `alt` w sekwencji „sprawdz-prawa-dostepu” | `BrakUprawnienException` | `KontrolaDostepu` |
| Utwórz plik | U | `02-sekwencja-utworz-plik.puml` | `SystemPlikow.utworzPlik` | `SystemPlikow`, `KontrolaDostepu`, `Katalog`, `Plik`, `PrawaDostepu` |
| Utwórz katalog | U | brak, analogiczna do „utworz-plik” | `SystemPlikow.utworzKatalog` | jak wyżej, z `Katalog` zamiast `Plik` |
| Usuń element | U | wstęp sekwencji „usun-katalog-z-zawartoscia” (odmowa dla niepustego katalogu) | `SystemPlikow.usun` | `SystemPlikow`, `KontrolaDostepu`, `Katalog` |
| Usuń katalog wraz z zawartością (`extend` Usuń) | U | `02-sekwencja-usun-katalog-z-zawartoscia.puml` | `SystemPlikow.usunKatalogRekurencyjnie` | `SystemPlikow`, `KontrolaDostepu`, `Katalog` |
| Przenieś element | U | `02-sekwencja-przenies-element.puml` | `SystemPlikow.przenies` | `SystemPlikow`, `KontrolaDostepu`, `WezelSystemuPlikow`, `Katalog` |
| Zmień nazwę elementu | U | brak | `SystemPlikow.zmienNazwe` | `SystemPlikow`, `KontrolaDostepu`, `WezelSystemuPlikow` |
| Wyświetl zawartość katalogu | U | brak | `SystemPlikow.listuj` | `SystemPlikow`, `KontrolaDostepu`, `Katalog` |
| Przejdź do katalogu | U | brak | `SystemPlikow.przejdzDo` | `SystemPlikow`, `KontrolaDostepu`, `Katalog` |
| Odczytaj / zapisz zawartość pliku | U | brak | `SystemPlikow.odczytajPlik`, `zapiszPlik` | `SystemPlikow`, `KontrolaDostepu`, `Plik` |
| Zmień prawa dostępu | U (właściciel lub A) | `02-sekwencja-zmien-prawa-dostepu.puml` | `SystemPlikow.zmienPrawa` | `SystemPlikow`, `KontrolaDostepu`, `WezelSystemuPlikow`, `PrawaDostepu` |
| Zmień właściciela | A | brak | `SystemPlikow.zmienWlasciciela` | `SystemPlikow`, `KontrolaDostepu`, `RejestrUzytkownikow`, `WezelSystemuPlikow` |
| Utwórz konto użytkownika | A | `02-sekwencja-utworz-konto-uzytkownika.puml` | `SystemPlikow.utworzKonto` | `SystemPlikow`, `KontrolaDostepu`, `RejestrUzytkownikow`, `Uzytkownik`, `UzytkownicyZapis` |
| Zmień hasło użytkownika | A | `02-sekwencja-zmien-haslo-uzytkownika.puml` | `SystemPlikow.zmienHaslo` | `SystemPlikow`, `KontrolaDostepu`, `RejestrUzytkownikow`, `Uzytkownik`, `UzytkownicyZapis` |
| Zapisz strukturę do pliku | U | `02-sekwencja-zapisz-strukture-do-pliku.puml` | `SystemPlikow.zapiszDoPliku` | `SystemPlikow`, `SystemPlikowZapis`, `WezelDane`, `WezelSystemuPlikow` |
| Wczytaj strukturę z pliku | U | `02-sekwencja-wczytaj-strukture-z-pliku.puml` | `SystemPlikow.wczytajZPliku` | `SystemPlikow`, `SystemPlikowZapis`, `WezelDane`, `RejestrUzytkownikow` |

Poza przypadkami użycia: `SystemPlikow.zainicjujAdministratora` realizuje notatkę o pierwszym koncie administratora
z `../../../../Downloads/01-przypadki-uzycia.puml` (start systemu, gdy nie ma kont).

## Które reguły uprawnień stosuje która operacja

| Metoda `KontrolaDostepu` | Używana przez |
|---|---|
| `wymagaj(u, w, p)` | `utworzPlik`, `utworzKatalog`, `usun`, `usunKatalogRekurencyjnie`, `przenies`, `zmienNazwe`, `listuj`, `przejdzDo`, `odczytajPlik`, `zapiszPlik` |
| `wymagajWlascicielaLubAdministratora(u, w)` | `zmienPrawa` |
| `wymagajAdministratora(u)` | `utworzKonto`, `zmienHaslo`, `zmienWlasciciela` |

Dla `utworzPlik`, `usunKatalogRekurencyjnie` i `przenies` jest to narysowane w sekwencjach. Dla pozostałych wynika
z `DECYZJE.md` (D8, D8a).

## Jak używać tej tabeli

- **Implementujesz funkcję:** otwórz sekwencję z kolumny „Sekwencja”, potem sygnatury w `KLASY.md`.
- **Zmieniasz metodę:** znajdź ją w kolumnie „Metoda wejściowa” i w sekwencjach, które ją wywołują. Zaktualizuj
  diagram klas, sekwencje i `KLASY.md`.
- **Dodajesz przypadek użycia:** dopisz wiersz, narysuj `02-sekwencja-<nazwa>.puml`, dodaj metodę do diagramu klas.
