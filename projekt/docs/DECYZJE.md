# Decyzje projektowe

Status: **przyjęte** = odzwierciedlone w diagramach `.puml`. **propozycja** = spójne z diagramami, ale nie narysowane
i nie potwierdzone. **do uzupełnienia** = numer występuje w `PLAN.md`, ale treści decyzji nie ma w repozytorium.
**otwarte** = wymaga decyzji użytkownika.

> **Uwaga:** plik odtworzono po usunięciu poprzedniej wersji (commit `04f3bdb`). D1–D17 pochodzą z jej historii,
> poprawione tam, gdzie diagramy zmieniły się w commicie `647210b`. D18–D33 wyprowadzono z diagramów
> (`03-diagram-klas`, `02-sekwencja-*`, `01-przypadki-uzycia`). Numery i zakres D18–D31 trzeba potwierdzić.

## Architektura

| ID | Decyzja | Status |
|---|---|---|
| D1 | **Bez Singletona.** Jeden obiekt `SystemPlikow` tworzy `Aplikacja.start()` i przekazuje kontrolerom (`ustawSystem`). Zależności przez konstruktor: `SystemPlikow(rejestr, zapisDrzewa, zapisKont)`. | przyjęte |
| D2 | **`SystemPlikow` jest fasadą** dla operacji użytkownika. Nie zawiera reguł uprawnień ani I/O. | przyjęte |
| D3 | **Wzorzec Composite:** `Plik` i `Katalog` dziedziczą po abstrakcyjnym `WezelSystemuPlikow`. | przyjęte |
| D4 | **Kontrola dostępu w osobnej klasie `KontrolaDostepu`** (też reguła „tylko administrator”). Odpowiada przypadkowi użycia „Sprawdź prawa dostępu” (`include`). | przyjęte |
| D5 | **Warstwa prezentacji poza modelem.** Model nie importuje JavaFX. | przyjęte |

## Uprawnienia i konta

| ID | Decyzja | Status |
|---|---|---|
| D6 | **Model uniksowy:** `Uprawnienie` = ODCZYT, ZAPIS, WYKONANIE. `PrawaDostepu` ma zbiór praw właściciela i praw pozostałych (`nadaj`, `odbierz`, `czyPozwala`). | przyjęte |
| D7 | **Administrator ma dostęp do wszystkiego.** Właściciel używa praw właściciela, reszta praw pozostałych. | przyjęte |
| D8 | **Wymagane uprawnienia:** utwórz plik/katalog = ZAPIS na bieżącym katalogu. Przenieś = ZAPIS na katalogu źródłowym i docelowym. Usuń (też rekurencyjnie) = ZAPIS na katalogu nadrzędnym. Zmiana praw = właściciel lub administrator. Konta, hasła, zmiana właściciela, zapis i wczytanie całej struktury = tylko administrator. | przyjęte |
| D8a | Pozostałe: wyświetl katalog = ODCZYT, przejdź do katalogu = WYKONANIE, odczyt pliku = ODCZYT, zapis pliku = ZAPIS, zmiana nazwy = ZAPIS na katalogu nadrzędnym. | propozycja |
| D9 | **Hasła tylko jako hash BCrypt** (`Uzytkownik.hashHasla`). | przyjęte |
| D10 | **Logowanie zwraca ten sam błąd** (`BladLogowaniaException`) dla złego loginu i złego hasła. | przyjęte |
| D11 | **Pierwszy administrator:** gdy rejestr kont jest pusty, `zainicjujAdministratora(haslo)` tworzy konto administratora. Konta tworzy potem tylko administrator. | przyjęte |

## Persystencja

| ID | Decyzja | Status |
|---|---|---|
| D12 | **JSON przez Gson, dwa niezależne zapisy:** drzewo (`SystemPlikowZapis`, ścieżkę pliku podaje wywołujący, np. z `FileChooser`) i konta (`UzytkownicyZapis`, ścieżka w konstruktorze). | przyjęte |
| D13 | **Płaski format `WezelDane`.** Właściciel zapisany jako `wlascicielLogin`. Przy wczytaniu `doWezla(rejestr)` odtwarza referencję z `RejestrUzytkownikow`, więc węzły jednego właściciela wskazują na ten sam obiekt. | przyjęte |
| D14 | **Wczytanie buduje nowe drzewo w całości**, dopiero potem zastępuje `korzen` i ustawia `biezacyKatalog = korzen`. Przy błędzie obecna struktura zostaje nietknięta. | przyjęte |
| D15 | **Konta zapisują się automatycznie** po `utworzKonto` i `zmienHaslo`. Drzewo zapisuje się na żądanie. | przyjęte |
| D16 | **Kolejność startu:** wczytaj konta → `new SystemPlikow(...)` → jeśli rejestr pusty `zainicjujAdministratora` → logowanie → opcjonalnie `wczytajZPliku(plik)` (tylko administrator). | przyjęte |

## Błędy

| ID | Decyzja | Status |
|---|---|---|
| D17 | **Hierarchia wyjątków:** `SystemPlikowException` ← `BrakUprawnienException`, `BladLogowaniaException`, `BladOperacjiException`. `BladOperacjiException` obejmuje: ścieżka nie istnieje, element już istnieje, niepusty katalog, cykl przy przenoszeniu, błąd I/O, nieznany właściciel w pliku. | przyjęte |

## Zmiany z etapu 0 (D18–D33)

| ID | Decyzja | Status |
|---|---|---|
| D18 | **Konto administratora to `root`** (`SystemPlikow.LOGIN_ROOT`). Korzeń `/` i `/home` (`NAZWA_KATALOGU_DOMOWEGO`) należą do `root`. | przyjęte (z notatki diagramu klas), zakres do potwierdzenia |
| D19 | **Każde konto ma katalog domowy `/home/<login>`**, tworzony przy `utworzKonto` (`utworzKatalogDomowy`). Właściciel: nowy użytkownik. Prawa domyślne: właściciel wszystko, pozostali ODCZYT i WYKONANIE. Bez sprawdzania praw do `/home`, bo administrator przeszedł już `wymagajAdministratora`. | przyjęte |
| D20 | **Zwykły użytkownik zapisuje i wczytuje tylko swój katalog domowy** (`zapiszKatalogDomowy`, `wczytajKatalogDomowy`, prawo ZAPIS do niego). Całość: tylko administrator. | przyjęte |
| D21 | **Wczytany katalog domowy należy w całości do wczytującego:** `doWezlaZWlascicielem(zalogowany)` ignoruje `wlascicielLogin` z pliku. Wczytanie podmienia zawartość `/home/<login>`, sam katalog (nazwa, właściciel, prawa) zostaje. | przyjęte |
| D22–D24 | Numery występują w zakresie „D18–D30” z `PLAN.md` (etap 0), treści brak w repozytorium. | do uzupełnienia |
| D25 | **Kontrolery GUI (`Aplikacja`, `LogowanieKontroler`, `GlownyKontroler`, `AdministratorKontroler`) są na diagramie klas.** Widoki w FXML (`src/main/resources/systemplikow/gui/`), kontrolery łapią `SystemPlikowException` i pokazują komunikat. | przyjęte |
| D26 | Numer cytowany w `PLAN.md` przy zadaniu B3 (sesja i konta), treści brak w repozytorium. | do uzupełnienia |
| D27 | **Korzeń `/` jest chroniony:** nie można go usunąć, przenieść ani zmienić mu nazwy (`BladOperacjiException`). | propozycja (z `PLAN.md` A4 i starego pytania otwartego nr 5) |
| D28 | **Konstruktory klas są na diagramie** (np. `Katalog(nazwa, wlasciciel)`, `Plik(nazwa, wlasciciel, tresc)`, `Uzytkownik(login, haslo, rola)`, wyjątki z opcjonalną `przyczyna`). | przyjęte |
| D29–D30 | Numery występują w zakresie „D18–D30” z `PLAN.md` (etap 0), treści brak w repozytorium. | do uzupełnienia |
| D31 | **Wyjątki są sprawdzane:** `SystemPlikowException extends Exception`. | przyjęte |
| D32 | **Po zalogowaniu `biezacyKatalog` = `/home/<login>`.** Gdy katalogu domowego nie ma, `biezacyKatalog` = korzeń. | przyjęte |
| D33 | **Po wczytaniu całości brakujące `/home` lub `/home/<login>` są dotwarzane** (`uzupelnijKatalogiDomowe`): `/home` należy do `root`, `/home/<login>` do konta, prawa domyślne. Węzeł `/home/<login>`, który nie jest katalogiem, przerywa wczytanie (`BladOperacjiException`). | przyjęte |
| D34 | **`setPrawa` zapisuje kopię, `getPrawa` zwraca kopię** (`new PrawaDostepu(prawa)`, konstruktor kopiujący). `PrawaDostepu` jest mutowalna, więc węzeł nie współdzieli obiektu z wywołującym (np. GUI albo ten sam obiekt przekazany dla dwóch węzłów), a zmiana praw jest możliwa tylko przez `setPrawa`, czyli po sprawdzeniu `KontrolaDostepu` (reguła 3). Skutek: `wezel.getPrawa().nadaj(...)` nic nie zmienia. Żeby zmienić prawa, trzeba pobrać kopię, zmodyfikować ją i przekazać do `SystemPlikow.zmienPrawa`. Nowy węzeł dostaje domyślne prawa z konstruktora `PrawaDostepu()`. | propozycja (zmiana diagramu, wymaga zgody drugiej osoby, reguła 1) |

## Uproszczenia (świadome)

- Usuwanie rekurencyjne sprawdza ZAPIS tylko na katalogu nadrzędnym, nie na każdym elemencie poddrzewa.
- `znajdzWezel(sciezka)` nie sprawdza prawa WYKONANIE na katalogach po drodze.
- Nie ma przypadku użycia „usuń konto”. Konta można tylko tworzyć i zmieniać im hasła.
- Przenoszenie katalogu do własnego podkatalogu jest blokowane (`czyLezyWPoddrzewie`).

## Pytania otwarte (zgłoś użytkownikowi, nie zgadywaj)

1. Jaki wyjątek rzuca `wymagajZalogowanego()`, gdy nikt nie jest zalogowany? Propozycja: `BrakUprawnienException`.
2. Czy `zmienWlasciciela` ma wymagać, żeby nowy właściciel istniał w rejestrze? Propozycja: tak, inaczej `BladOperacjiException`.
3. Uzupełnić treść D22–D24, D26, D29–D30 (zob. wyżej) i potwierdzić zakres D18, D27.
