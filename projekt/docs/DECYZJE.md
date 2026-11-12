# Decyzje projektowe

Status: **przyjęte** = odzwierciedlone w diagramach `.puml`. **propozycja** = spójne z diagramami, ale nie narysowane
i nie potwierdzone. **otwarte** = wymaga decyzji użytkownika.

## Architektura

| ID | Decyzja | Uzasadnienie | Status |
|---|---|---|---|
| D1 | **Bez Singletona.** Jeden obiekt `SystemPlikow` tworzy `main()` i przekazuje konstruktorem (`SystemPlikow(rejestr, zapisDrzewa, zapisKont)`). | Singleton wprowadza globalny stan i ukrywa zależności. Instrukcja kursu zaleca zwykły obiekt z przekazaniem. | przyjęte |
| D2 | **`SystemPlikow` jest fasadą** dla operacji użytkownika (zaloguj, utwórz, usuń, przenieś, prawa, konta, zapis). Nie zawiera reguł uprawnień ani I/O. | Pojedyncza odpowiedzialność (SRP). | przyjęte |
| D3 | **Wzorzec Composite:** `Plik` i `Katalog` dziedziczą po abstrakcyjnym `WezelSystemuPlikow`. Katalog jest kompozycją swoich dzieci. | Jednolita obsługa plików i katalogów, rekurencyjna struktura drzewa. | przyjęte |
| D4 | **Kontrola dostępu w osobnej klasie `KontrolaDostepu`.** Zawiera też regułę „tylko administrator”. | Odpowiada przypadkowi użycia „Sprawdź prawa dostępu” (`include`), SRP. | przyjęte |
| D5 | **Warstwa prezentacji poza modelem.** W sekwencjach to uczestnik `Interfejs (obsługa)`. Kontrolery JavaFX nie są jeszcze zaprojektowane. | Rozdzielenie modelu i GUI (MVC). | przyjęte |

## Uprawnienia i konta

| ID | Decyzja | Status |
|---|---|---|
| D6 | **Model uprawnień w stylu uniksowym:** `Uprawnienie` = ODCZYT, ZAPIS, WYKONANIE. `PrawaDostepu` ma zbiór praw właściciela i zbiór praw pozostałych. | przyjęte |
| D7 | **Administrator ma dostęp do wszystkiego**, niezależnie od praw węzła. Właściciel używa praw właściciela, reszta praw pozostałych. | przyjęte |
| D8 | **Wymagane uprawnienia:** utwórz plik lub katalog = ZAPIS na bieżącym katalogu. Przenieś = ZAPIS na katalogu źródłowym i docelowym. Usuń (też rekurencyjnie) = ZAPIS na katalogu nadrzędnym. Zmiana praw = właściciel lub administrator. Konta, hasła i zmiana właściciela = tylko administrator. | przyjęte dla operacji narysowanych w sekwencjach |
| D8a | Pozostałe operacje: wyświetl katalog = ODCZYT, przejdź do katalogu = WYKONANIE, odczyt pliku = ODCZYT, zapis pliku = ZAPIS, zmiana nazwy = ZAPIS na katalogu nadrzędnym. | propozycja |
| D9 | **Hasła tylko jako hash BCrypt** (`Uzytkownik.hashHasla`). `sprawdzHaslo` i `ustawHaslo` korzystają z jBCrypt. | przyjęte |
| D10 | **Logowanie zwraca ten sam błąd** dla złego loginu i złego hasła (`BladLogowaniaException`), żeby nie ujawniać, który login istnieje. | przyjęte |
| D11 | **Pierwszy administrator:** gdy rejestr kont jest pusty, `SystemPlikow.zainicjujAdministratora(login, haslo)` tworzy konto administratora. Konta tworzy potem tylko administrator. | przyjęte |

## Persystencja

| ID | Decyzja | Status |
|---|---|---|
| D12 | **Format JSON przez Gson, dwa niezależne pliki:** drzewo (`SystemPlikowZapis`) i konta (`UzytkownicyZapis`). Liczba klas zapisu = liczba niezależnych agregatów danych. | przyjęte |
| D13 | **Płaski format zapisu `WezelDane`.** Właściciel zapisany jako `wlascicielLogin`, nie jako obiekt (bez duplikacji użytkowników w JSON). Przy wczytaniu `doWezla(rejestr)` odtwarza referencję z `RejestrUzytkownikow`, więc węzły jednego właściciela wskazują na ten sam obiekt. | przyjęte |
| D14 | **Wczytanie buduje nowe drzewo w całości**, dopiero potem zastępuje `korzen` i ustawia `biezacyKatalog = korzen`. Przy błędzie obecna struktura zostaje nietknięta. | przyjęte |
| D15 | **Konta zapisują się automatycznie** po `utworzKonto` i `zmienHaslo` (`UzytkownicyZapis.zapisz(rejestr)`). Drzewo zapisuje się na żądanie (`zapiszDoPliku`). | przyjęte |
| D16 | **Kolejność startu:** wczytaj konta → `new SystemPlikow(...)` → jeśli rejestr pusty `zainicjujAdministratora` → logowanie → opcjonalnie `wczytajZPliku()` (wymaga zalogowania). | przyjęte |

## Błędy

| ID | Decyzja | Status |
|---|---|---|
| D17 | **Hierarchia wyjątków:** `SystemPlikowException` ← `BrakUprawnienException`, `BladLogowaniaException`, `BladOperacjiException`. `BladOperacjiException` obejmuje: ścieżka nie istnieje, element już istnieje, niepusty katalog, cykl przy przenoszeniu, błąd I/O, nieznany właściciel w pliku. | przyjęte |

## Uproszczenia (świadome)

- Usuwanie rekurencyjne sprawdza ZAPIS tylko na katalogu nadrzędnym, nie na każdym elemencie poddrzewa.
- `znajdzWezel(sciezka)` nie sprawdza prawa WYKONANIE na katalogach po drodze.
- Nie ma przypadku użycia „usuń konto”. Konta można tylko tworzyć i zmieniać im hasła.
- Przenoszenie katalogu do własnego podkatalogu jest blokowane (`czyLezyWPoddrzewie`).

## Pytania otwarte (zgłoś użytkownikowi, nie zgadywaj)

1. Czy „Wczytaj strukturę z pliku” ma być tylko dla administratora? Obecnie mogą ją wykonać wszyscy zalogowani, a wczytanie nadpisuje całe drzewo.
2. Jakie są domyślne prawa nowego węzła (konstruktor `PrawaDostepu()`)? Diagramy tego nie ustalają.
3. Jaki wyjątek rzuca `wymagajZalogowanego()`, gdy nikt nie jest zalogowany? Diagramy go nie pokazują. Propozycja: `BrakUprawnienException`.
4. Czy `zmienWlasciciela` ma wymagać, żeby nowy właściciel istniał w rejestrze? Propozycja: tak, inaczej `BladOperacjiException`.
5. Czy korzeń `/` można zmieniać (nazwa, przeniesienie)? Propozycja: nie, `BladOperacjiException`.
