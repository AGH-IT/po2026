# Plan prac — podział na dwóch programistów

Projekt robią dwie osoby. **Programista A** odpowiada za drzewo plików i uprawnienia, **programista B** za konta,
sesję i zapis do pliku. Podział idzie wzdłuż pakietów i przypadków użycia, żeby każdy miał „swoje” pliki i jak
najmniej konfliktów w Gicie. Jedyny wspólny plik kodu to fasada `SystemPlikow` (patrz „Zasady współpracy”).

Terminy: deklaracja projektu 15.12.2026, oddanie (push) 27.01.2027.

**Dla agenta:** zanim zaczniesz, ustal z użytkownikiem, czy pracuje jako A czy B, i bierz tylko zadania z jego
kolumny, których zależności są już odhaczone. Po skończeniu zadania odhacz je tutaj (`[x]`) w tym samym commicie.

## Etap 0 — razem, blokuje wszystko

- [x] Rozstrzygnąć luki w diagramie klas, nanieść je na diagramy, `KLASY.md`, `TRACEABILITY.md`, `DECYZJE.md`
      (konstruktory, konto root, katalogi domowe, zapis kont, GUI: D18–D30).
- [x] Rozstrzygnąć 3 nowe pytania otwarte z `DECYZJE.md` (wyjątki sprawdzane czy nie, katalog po zalogowaniu,
      brakujący katalog domowy po wczytaniu całości).
- [x] `pom.xml`, Maven Wrapper, `render.sh` działają na obu komputerach (`./mvnw test`, `uml/render.sh`).
- [ ] Drugi programista sprawdził u siebie: `./mvnw test` i `uml/render.sh` kończą się kodem 0.
- [ ] Ustalić z prowadzącym, jak druga osoba ma dostęp do forka (README kursu: projekt w tym samym forku co lab).

## Etap 1 — fundamenty (równolegle)

| ID | Programista A — drzewo i uprawnienia | Zależy od |
|---|---|---|
| A1 | [ ] `Uprawnienie`, `PrawaDostepu` + testy `czyPozwala` | etap 0 |
| A2 | [ ] `WezelSystemuPlikow`, `Plik`, `Katalog` + testy (`dodajDziecko` ustawia rodzica, `getSciezka`, `czyLezyWPoddrzewie`, `policzPotomkow`, `getRozmiar`) | A1, B1 (`Uzytkownik` jako właściciel) |

| ID | Programista B — konta i wyjątki | Zależy od |
|---|---|---|
| B1 | [ ] Pakiet `wyjatki` (4 klasy, sprawdzane `extends Exception` wg D31, konstruktory wg D28), `RolaUzytkownika`, `Uzytkownik` (BCrypt), `RejestrUzytkownikow` + testy (hasło nie jest przechowywane jawnie, `sprawdzHaslo`, duplikat loginu). **Zrobić najpierw, A2 i A3 tego potrzebują.** | etap 0 |
| B2 | [ ] Szkielet `SystemPlikow`: pola, stałe, konstruktor (buduje `/`, `/home`, `/home/<login>`, D18, D19), `utworzKatalogDomowy`, `znajdzKatalogDomowy`, `uzupelnijKatalogiDomowe`, a **pozostałe** metody z diagramu jako `throw new UnsupportedOperationException()`. Szybki merge do `main`. | B1, A2 |

## Etap 2 — logika (równolegle)

| ID | Programista A | Zależy od |
|---|---|---|
| A3 | [ ] `KontrolaDostepu` + testy reguł D7, D8, D8a (administrator, właściciel, pozostali, `wymagajAdministratora`). Priorytet testów z `AGENTS.md`. | A2, B1 |
| A4 | [ ] `SystemPlikow`, operacje na drzewie: `znajdzWezel`, `utworzPlik`, `utworzKatalog`, `usun`, `usunKatalogRekurencyjnie`, `przenies`, `zmienNazwe`, `przejdzDo`, `listuj`, `odczytajPlik`, `zapiszPlik`, `zmienPrawa`, `zmienWlasciciela` + ochrona korzenia (D27) + testy wg sekwencji `02-*` | A3, B2 |

| ID | Programista B | Zależy od |
|---|---|---|
| B3 | [ ] `SystemPlikow`, sesja i konta: `wymagajZalogowanego`, `zaloguj`, `wyloguj`, `zainicjujAdministratora` (konto root), `utworzKonto` (z katalogiem domowym), `zmienHaslo` + testy (D10, D11, D15, D19, D26, D32: po zalogowaniu `biezacyKatalog` = `/home/<login>`) | B2, A3 (`wymagajAdministratora`) |
| B4 | [ ] `UzytkownicyZapis` + test zapis → odczyt kont | B1 |
| B5 | [ ] `RodzajWezla`, `WezelDane` (`doWezla`, `doWezlaZWlascicielem`), `SystemPlikowZapis` + testy: zapis → odczyt daje to samo drzewo i prawa, węzły jednego właściciela wskazują na ten sam obiekt (D13), nieznany właściciel → `BladOperacjiException`, wczytany katalog domowy należy do wczytującego (D21) | A2, B1 |
| B6 | [ ] `SystemPlikow.zapiszDoPliku`, `wczytajZPliku` (tylko administrator), `zapiszKatalogDomowy`, `wczytajKatalogDomowy` + testy D14, D20, D33 (wczytanie pliku bez `/home/<login>` dotwarza katalog; błąd wczytania nie psuje obecnego drzewa, zwykły użytkownik nie zapisze całości) | B5, B2, A3 |

## Etap 3 — GUI JavaFX, FXML (równolegle; kontrolery są już na diagramie klas, D25)

| ID | Programista A | Zależy od |
|---|---|---|
| A5 | [ ] Główne okno: drzewo katalogów, lista zawartości, przejdź, utwórz, usuń (z pytaniem o usunięcie rekurencyjne), przenieś, zmień nazwę | A4, B7 |
| A6 | [ ] Edycja treści pliku, okno zmiany praw i właściciela, zapis / wczytanie katalogu domowego (`FileChooser`) | A5 |

| ID | Programista B | Zależy od |
|---|---|---|
| B7 | [ ] `Aplikacja` (kolejność startu D16, plik kont `dane/konta.json`), `LogowanieKontroler` z ekranem pierwszego administratora (hasło dla root) | B3, B4 |
| B8 | [ ] `AdministratorKontroler`: utwórz konto, zmień hasło, zapisz / wczytaj całą strukturę (`FileChooser`) | B7, B6 |

## Etap 4 — razem, przed oddaniem

- [ ] Scenariusz całościowy ręcznie w GUI: pierwszy administrator (root) → konto użytkownika → pliki i prawa w
      `/home/<login>` → zapis katalogu domowego i całości → restart → wczytanie.
- [ ] Przegląd zgodności: każda klasa i metoda z `03-diagram-klas.puml` istnieje w kodzie pod tą samą nazwą i odwrotnie.
- [ ] README projektu: jak zbudować i uruchomić (`./mvnw javafx:run`), konto startowe.

## Zasady współpracy

- **Gałęzie:** jedna gałąź na zadanie (`a/A3-kontrola-dostepu`, `b/B5-persystencja`), merge do `main` po
  zielonym `./mvnw test`. Małe i częste merge, szczególnie przy `SystemPlikow`.
- **`SystemPlikow` jest wspólny.** Szkielet (B2) wchodzi do `main` jak najwcześniej. Potem A zmienia tylko ciała
  metod z A4, a B tylko z B3 i B6. Nie przestawiajcie kolejności metod i nie formatujcie całego pliku.
- **Diagram klas jest wspólny.** Zmiana sygnatury lub nowa klasa wymaga zgody drugiej osoby. Zmiana diagramu idzie
  osobnym commitem przed kodem (zob. `AGENTS.md`, reguła 2).
- **Testy:** każdy pisze testy do swoich klas w `src/test/java/systemplikow/<pakiet>/`. Testy drugiej osoby
  nie mogą się sypać po waszym merge'u.
- **Pytania otwarte** z `DECYZJE.md` rozstrzygacie razem i dopisujecie decyzję z nowym numerem `D…`.
