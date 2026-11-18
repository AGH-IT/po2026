package systemplikow.model;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * Prawa dostępu węzła w modelu uniksowym (D6): osobny zbiór uprawnień dla właściciela
 * i dla pozostałych użytkowników. Klasa nie wie nic o administratorze (D7) — to rozstrzyga
 * {@code KontrolaDostepu}.
 */
public class PrawaDostepu {

    private final Set<Uprawnienie> prawaWlasciciela = EnumSet.noneOf(Uprawnienie.class);
    private final Set<Uprawnienie> prawaPozostalych = EnumSet.noneOf(Uprawnienie.class);

    /**
     * Prawa domyślne (D19, D33): właściciel ma wszystko, pozostali tylko ODCZYT i WYKONANIE.
     */
    public PrawaDostepu() {
        prawaWlasciciela.addAll(EnumSet.allOf(Uprawnienie.class));
        prawaPozostalych.add(Uprawnienie.ODCZYT);
        prawaPozostalych.add(Uprawnienie.WYKONANIE);
    }

    /**
     * Czy podane uprawnienie jest przyznane.
     *
     * @param jestWlascicielem {@code true} — sprawdzane są prawa właściciela, {@code false} — prawa pozostałych
     */
    public boolean czyPozwala(Uprawnienie uprawnienie, boolean jestWlascicielem) {
        Objects.requireNonNull(uprawnienie, "uprawnienie");
        return zbior(jestWlascicielem).contains(uprawnienie);
    }

    /** Przyznaje uprawnienie właścicielowi ({@code dlaWlasciciela == true}) albo pozostałym. Jest idempotentne. */
    public void nadaj(Uprawnienie uprawnienie, boolean dlaWlasciciela) {
        Objects.requireNonNull(uprawnienie, "uprawnienie");
        zbior(dlaWlasciciela).add(uprawnienie);
    }

    /** Odbiera uprawnienie właścicielowi ({@code dlaWlasciciela == true}) albo pozostałym. Jest idempotentne. */
    public void odbierz(Uprawnienie uprawnienie, boolean dlaWlasciciela) {
        Objects.requireNonNull(uprawnienie, "uprawnienie");
        zbior(dlaWlasciciela).remove(uprawnienie);
    }

    /** Kopia zbioru praw właściciela; zmiana kopii nie wpływa na obiekt (do zmian służą {@link #nadaj}/{@link #odbierz}). */
    public Set<Uprawnienie> getPrawaWlasciciela() {
        return kopia(prawaWlasciciela);
    }

    /** Kopia zbioru praw pozostałych użytkowników. */
    public Set<Uprawnienie> getPrawaPozostalych() {
        return kopia(prawaPozostalych);
    }

    /**
     * Zapis uniksowy, najpierw właściciel, potem pozostali, np. {@code rwxr-x} dla praw domyślnych.
     * Brak uprawnienia to {@code -}.
     */
    @Override
    public String toString() {
        return symbole(prawaWlasciciela) + symbole(prawaPozostalych);
    }

    private Set<Uprawnienie> zbior(boolean dlaWlasciciela) {
        return dlaWlasciciela ? prawaWlasciciela : prawaPozostalych;
    }

    private static Set<Uprawnienie> kopia(Set<Uprawnienie> zrodlo) {
        return zrodlo.isEmpty() ? EnumSet.noneOf(Uprawnienie.class) : EnumSet.copyOf(zrodlo);
    }

    private static String symbole(Set<Uprawnienie> zbior) {
        return (zbior.contains(Uprawnienie.ODCZYT) ? "r" : "-")
                + (zbior.contains(Uprawnienie.ZAPIS) ? "w" : "-")
                + (zbior.contains(Uprawnienie.WYKONANIE) ? "x" : "-");
    }
}
