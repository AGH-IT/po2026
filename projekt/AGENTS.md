# Symulator Systemu Plików — instrukcje dla agenta

Projekt zaliczeniowy z przedmiotu Programowanie obiektowe 2026/27. Aplikacja symuluje system plików w pamięci:
drzewo katalogów i plików, prawa dostępu, konta użytkowników z logowaniem oraz zapis całej struktury do pliku.
Dokumentacja i komentarze są po polsku. Identyfikatory w kodzie i diagramach są bez polskich znaków
(`Uzytkownik`, `WezelSystemuPlikow`).

## Wymagania (z treści zadania)

- Struktura drzewa katalogów i plików w pamięci.
- Operacje: utwórz, usuń, przenieś, prawa dostępu.
- Serializacja całej struktury do pliku.
- Możliwość logowania.
- **Tylko administrator** może tworzyć nowych użytkowników i zmieniać ich hasła.

## Stack

Zgodnie z materiałem kursowym (do potwierdzenia z prowadzącym): Java, JavaFX w układzie Model-View-Controller,
JSON przez Gson, hasła przez jBCrypt, diagramy w PlantUML. Kodu jeszcze nie ma. Powstały diagramy.

## Układ projektu

```
projekt/
  src/    kod aplikacji (układ Mavena: src/main/java, src/main/resources, src/test/java)
  uml/    diagramy .puml i render.sh
  docs/   dokumentacja .md dla agenta (DECYZJE, KLASY, TRACEABILITY)
```

- `pom.xml` leży w `projekt/` (obok `src/`), bo Maven oczekuje `src/main/java` obok `pom.xml`.
- Pakiety Javy odpowiadają pakietom z diagramu klas: `systemplikow.model`, `systemplikow.wyjatki`,
  `systemplikow.persystencja`, `systemplikow.gui` (kontrolery JavaFX). Klasa `X` to plik
  `src/main/java/systemplikow/<pakiet>/X.java`.
- Testy w `src/test/java/systemplikow/`. Priorytet: reguły `KontrolaDostepu` i serializacja (`persystencja`).
- Budowanie i testy (po dodaniu `pom.xml`): `mvn -f projekt/pom.xml test`.

## Mapa dokumentacji

Czytaj w tej kolejności, zależnie od zadania:

| Plik | Do czego służy |
|---|---|
| `projekt/uml/01-przypadki-uzycia.puml` | Co system ma robić, aktorzy, `include` i `extend` |
| `projekt/uml/02-sekwencja-*.puml` | Dokładny przebieg każdego ważniejszego przypadku użycia (10 plików) |
| `projekt/uml/03-diagram-klas.puml` | Klasy, pola, sygnatury metod, relacje. Źródło prawdy dla kodu |
| `projekt/uml/04-diagram-obiektow.puml` | Przykładowy stan w pamięci (pomocniczy) |
| `projekt/docs/KLASY.md` | Zwięzła lista klas z odpowiedzialnościami i sygnaturami |
| `projekt/docs/TRACEABILITY.md` | Przypadek użycia → sekwencja → metoda → klasy |
| `projekt/docs/DECYZJE.md` | Decyzje projektowe, uproszczenia i pytania otwarte |

## Reguły pracy

1. **Diagram klas jest źródłem prawdy.** Nazwy klas, pól i metod w kodzie są identyczne jak w `03-diagram-klas.puml`.
   Klasa to jeden plik `.java`, pole `-` to pole prywatne, metoda `+` to metoda publiczna.
2. **Zmiana sygnatury lub relacji = najpierw diagram, potem kod.** Zaktualizuj `.puml`, `docs/KLASY.md` i
   `docs/TRACEABILITY.md` w tej samej zmianie, i uruchom `projekt/uml/render.sh`.
3. **Model nie zna GUI.** Pakiet `model` nie importuje niczego z JavaFX. Warstwa prezentacji (kontrolery) zna model.
   Wyjątek: interfejs `Obserwator`, jeśli zostanie dodany.
4. **Uprawnienia sprawdza wyłącznie `KontrolaDostepu`.** Operacje w `SystemPlikow` wywołują jej metody `wymagaj*`.
   Nie dubluj tych reguł w innych klasach. Reguła „tylko administrator” to `wymagajAdministratora`.
5. **Hasła tylko jako hash BCrypt.** Jawne hasło nie trafia do pola, pliku ani logu.
6. **Persystencja jest osobna od modelu.** Tylko klasy z pakietu `persystencja` znają Gson i `Files`.
   Właściciel węzła jest zapisywany jako login (`String`), nie jako obiekt (patrz `docs/DECYZJE.md`, D13).
7. **Wyjątki domenowe** dziedziczą po `SystemPlikowException`. Warstwa GUI je łapie i pokazuje jako tekst.
8. **Nie zmieniaj ustalonych decyzji** z `docs/DECYZJE.md` bez zgody użytkownika. Pytania otwarte tam wymienione
   zgłoś użytkownikowi zamiast zgadywać.

## Jak sprawdzić diagramy

```bash
projekt/uml/render.sh          # sprawdza składnię wszystkich .puml (kod wyjścia != 0 przy błędzie)
projekt/uml/render.sh png      # renderuje PNG do projekt/uml/render/
```

Wymaga polecenia `plantuml` w PATH albo zmiennej `PLANTUML_JAR` wskazującej plik `.jar`.

## Czego nie robić

- Nie edytuj wyrenderowanych PNG. Źródłem są pliki `.puml`.
- Nie dodawaj klas „na zapas”. Każda klasa wynika z uczestnika sekwencji lub z przypadku użycia.
- Nie przenoś logiki uprawnień, zapisu do pliku ani haszowania do klasy `SystemPlikow`. To fasada, nie „klasa-Bóg”.
