package agh.po.symulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class PozycjaTest {
    @Test
    public void testPrzemiesc(){
        Pozycja p1 = new Pozycja(0, 0);
        Pozycja cel1 = new Pozycja(3, 4);
        p1.przemiesc(0.1, 10, cel1);
        assertEquals("(0.6, 0.8)", p1.getPozycja());

        Pozycja p2 = new Pozycja(0, 0);
        Pozycja cel2 = new Pozycja(3, 4);
        p2.przemiesc(0.1, 100, cel2);
        assertEquals("(3.0, 4.0)", p2.getPozycja());

        Pozycja p3 = new Pozycja(2, 5);
        p3.przemiesc(0.1, 10, null);
        assertEquals("(2.0, 5.0)", p3.getPozycja());
    }
}



