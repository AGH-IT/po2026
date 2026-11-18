package systemplikow.model;

/**
 * Pojedyncze uprawnienie w stylu uniksowym (r, w, x).
 * Dla pliku: ODCZYT = czytanie treści, ZAPIS = zmiana treści, WYKONANIE = (symbolicznie) uruchomienie.
 * Dla katalogu: ODCZYT = listowanie, ZAPIS = tworzenie/usuwanie/przenoszenie elementów, WYKONANIE = wejście do katalogu.
 */
public enum Uprawnienie {
    ODCZYT,
    ZAPIS,
    WYKONANIE
}
