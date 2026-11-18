package systemplikow.model;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static systemplikow.model.Uprawnienie.*;

class PrawaDostepuTest {

    @Test
    void prawaDomyslneWlascicielMaWszystko() {
        PrawaDostepu prawa = new PrawaDostepu();

        assertTrue(prawa.czyPozwala(ODCZYT, true));
        assertTrue(prawa.czyPozwala(ZAPIS, true));
        assertTrue(prawa.czyPozwala(WYKONANIE, true));
    }

    @Test
    void prawaDomyslnePozostaliMajaOdczytIWykonanie() {
        PrawaDostepu prawa = new PrawaDostepu();

        assertTrue(prawa.czyPozwala(ODCZYT, false));
        assertFalse(prawa.czyPozwala(ZAPIS, false));
        assertTrue(prawa.czyPozwala(WYKONANIE, false));
    }

    @Test
    void odbierzUsuwaUprawnienieTylkoZWybranegoZbioru() {
        PrawaDostepu prawa = new PrawaDostepu();

        prawa.odbierz(ZAPIS, true);

        assertFalse(prawa.czyPozwala(ZAPIS, true));
        assertTrue(prawa.czyPozwala(ODCZYT, true));
        assertTrue(prawa.czyPozwala(ODCZYT, false));
    }

    @Test
    void nadajDodajeUprawnienieTylkoDoWybranegoZbioru() {
        PrawaDostepu prawa = new PrawaDostepu();

        prawa.nadaj(ZAPIS, false);

        assertTrue(prawa.czyPozwala(ZAPIS, false));
        assertEquals(EnumSet.allOf(Uprawnienie.class), prawa.getPrawaPozostalych());
        assertTrue(prawa.czyPozwala(ZAPIS, true));
    }

    @Test
    void nadajIOdbierzSaIdempotentne() {
        PrawaDostepu prawa = new PrawaDostepu();

        prawa.nadaj(ODCZYT, false);
        prawa.nadaj(ODCZYT, false);
        assertTrue(prawa.czyPozwala(ODCZYT, false));

        prawa.odbierz(ZAPIS, false); // pozostali i tak nie mieli ZAPIS
        prawa.odbierz(ODCZYT, false);
        prawa.odbierz(ODCZYT, false);
        assertFalse(prawa.czyPozwala(ODCZYT, false));
    }

    @Test
    void moznaOdebracWszystkoIPrzywrocic() {
        PrawaDostepu prawa = new PrawaDostepu();

        for (Uprawnienie u : Uprawnienie.values()) {
            prawa.odbierz(u, true);
            prawa.odbierz(u, false);
        }
        for (Uprawnienie u : Uprawnienie.values()) {
            assertFalse(prawa.czyPozwala(u, true));
            assertFalse(prawa.czyPozwala(u, false));
        }
        assertTrue(prawa.getPrawaWlasciciela().isEmpty());
        assertTrue(prawa.getPrawaPozostalych().isEmpty());

        prawa.nadaj(WYKONANIE, true);
        assertTrue(prawa.czyPozwala(WYKONANIE, true));
    }

    @Test
    void gettery_zwracajaKopieNiePozwalajaPsucStanu() {
        PrawaDostepu prawa = new PrawaDostepu();

        Set<Uprawnienie> wlasciciel = prawa.getPrawaWlasciciela();
        wlasciciel.clear();
        Set<Uprawnienie> pozostali = prawa.getPrawaPozostalych();
        pozostali.add(ZAPIS);

        assertTrue(prawa.czyPozwala(ZAPIS, true));
        assertFalse(prawa.czyPozwala(ZAPIS, false));
    }

    @Test
    void gettery_zwracajaAktualneZbiory() {
        PrawaDostepu prawa = new PrawaDostepu();

        assertEquals(EnumSet.allOf(Uprawnienie.class), prawa.getPrawaWlasciciela());
        assertEquals(EnumSet.of(ODCZYT, WYKONANIE), prawa.getPrawaPozostalych());
    }

    @Test
    void toStringPokazujePrawaWFormacieUniksowym() {
        PrawaDostepu prawa = new PrawaDostepu();
        assertEquals("rwxr-x", prawa.toString());

        prawa.odbierz(WYKONANIE, true);
        prawa.nadaj(ZAPIS, false);
        assertEquals("rw-rwx", prawa.toString());

        prawa.odbierz(ODCZYT, false);
        prawa.odbierz(ZAPIS, false);
        prawa.odbierz(WYKONANIE, false);
        assertEquals("rw----", prawa.toString());
    }

    @Test
    void nullJakoUprawnienieToNullPointerException() {
        PrawaDostepu prawa = new PrawaDostepu();

        assertThrows(NullPointerException.class, () -> prawa.czyPozwala(null, true));
        assertThrows(NullPointerException.class, () -> prawa.nadaj(null, true));
        assertThrows(NullPointerException.class, () -> prawa.odbierz(null, false));
    }
}
