package agh.po.lab3;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CodingBatTest {
    CodingBat cb = new CodingBat();

    @org.junit.Test
    public void sleepIn() {
        assertTrue(cb.sleepIn(false, false));
        assertFalse(cb.sleepIn(true, false));
        assertTrue(cb.sleepIn(false, true));
    }

    @org.junit.Test
    public void monkeyTrouble() {
        assertTrue(cb.monkeyTrouble(true, true));
        assertTrue(cb.monkeyTrouble(false, false));
        assertFalse(cb.monkeyTrouble(true, false));
    }

    @org.junit.Test
    public void countEvens() {
        assertEquals(3, cb.countEvens(new int[]{2, 1, 2, 3, 4}));
        assertEquals(5, cb.countEvens(new int[]{2, 2, 0, 4, 6}));
        assertEquals(0, cb.countEvens(new int[]{1, 3, 5}));
    }

    @org.junit.Test
    public void makeOutWord() {
        assertEquals("<<Yay>>", cb.makeOutWord("<<>>", "Yay"));
        assertEquals("<<WooHoo>>", cb.makeOutWord("<<>>", "WooHoo"));
        assertEquals("[[wneka]]", cb.makeOutWord("[[]]", "wneka"));
    }

    @org.junit.Test
    public void testMapAB() {

        Map<String, String> map1 = new HashMap<>(Map.of("a", "Hi", "b", "There"));
        Map<String, String> expected1 = Map.of("a", "Hi", "ab", "HiThere", "b", "There");
        assertEquals(expected1, cb.mapAB(map1));

        Map<String, String> map2 = new HashMap<>(Map.of("a", "Hi"));
        Map<String, String> expected2 = Map.of("a", "Hi");
        assertEquals(expected2, cb.mapAB(map2));

        Map<String, String> map3 = new HashMap<>(Map.of("b", "There"));
        Map<String, String> expected3 = Map.of("b", "There");
        assertEquals(expected3, cb.mapAB(map3));
    }

    @org.junit.Test
    public void testWordLen() {

        assertEquals(
                Map.of("bb", 2, "a", 1),
                cb.wordLen(new String[]{"a", "bb", "a", "bb"})
        );

        assertEquals(
                Map.of("that", 4, "and", 3, "this", 4),
                cb.wordLen(new String[]{"this", "and", "that", "and"})
        );

        assertEquals(
                Map.of("code", 4, "bug", 3),
                cb.wordLen(new String[]{"code", "code", "code", "bug"})
        );
    }

}