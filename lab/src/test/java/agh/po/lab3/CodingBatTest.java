package agh.po.lab3;

import static org.junit.Assert.*;

public class CodingBatTest {

    @org.junit.Test
    public void sleepIn() {
        CodingBat cb = new CodingBat();
        assertTrue(cb.sleepIn(false, false));
        assertFalse(cb.sleepIn(true, false));
        assertTrue(cb.sleepIn(false, true));
    }

    @org.junit.Test
    public void diff21() {
        CodingBat cb = new CodingBat();
        assertEquals(2, cb.diff21(19));
        assertEquals(11, cb.diff21(10));
        assertEquals(0, cb.diff21(21));
    }

    @org.junit.Test
    public void countEvens() {
        CodingBat cb = new CodingBat();
        int[] wynik = {3};
        int[] num1 = {2,1,2,3,4};
        assertArrayEquals(wynik, cb.countEvens(num1));
        //assertArrayEquals({3}, cb.countEvens({2,2,0}));
        //assertArrayEquals(1, cb.countEvens({2}));
    }

    @org.junit.Test
    public void helloName() {
        CodingBat cb = new CodingBat();
        assertEquals("Hello X!", cb.helloName("X"));
        assertEquals("Hello Omega!", cb.helloName("Omega"));
        assertEquals("Hello Hello!", cb.helloName("Hello"));
    }
}