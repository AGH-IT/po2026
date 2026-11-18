package agh.po.lab3;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
public class CodingBatTest {

    private CodingBat codingBat = new CodingBat();

    @org.junit.Test
    public void missingChar() {
        assertEquals("ktten", codingBat.missingChar("kitten",1));
        assertEquals("jaa", codingBat.missingChar("java",2));
        assertEquals("itten", codingBat.missingChar("kitten",0));
    }

    @org.junit.Test
    public void monkeyTrouble() {
        assertEquals(true,codingBat.monkeyTrouble(true,true));
        assertEquals(true,codingBat.monkeyTrouble(false,false));
        assertEquals(false,codingBat.monkeyTrouble(true,false));
        assertEquals(false,codingBat.monkeyTrouble(false,true));
    }

    @org.junit.Test
    public void countEvens() {
        assertEquals(2,codingBat.countEvens(new int[]{2,1,3,4}));
        assertEquals(4,codingBat.countEvens(new int[]{0,0,0,0}));
        assertEquals(0,codingBat.countEvens(new int[]{1,5,211,9}));
    }

    @org.junit.Test
    public void right2() {
        assertEquals("elHot", codingBat.right2("Hotel"));
        assertEquals("vaja", codingBat.right2("java"));
        assertEquals("Hi", codingBat.right2("Hi"));
        assertEquals("561234", codingBat.right2("123456"));
    }

    @org.junit.Test
    public  void mapAB(){
        assertEquals(
                Map.of("c", "cake"),
                codingBat.mapAB(new HashMap<>((Map.of("a", "aaa", "b", "aaa", "c", "cake"))))
        );
        assertEquals(
                Map.of(),
                codingBat.mapAB(new HashMap<>(Map.of()))
        );
        assertEquals(
                Map.of("a","aaaa"),
                codingBat.mapAB(new HashMap<>(Map.of("a","aaaa")))
        );
    }

    @org.junit.Test
    public void wordCount(){
        assertEquals(Map.of("a",2,"b",1),codingBat.wordCount(new String[]{"a","b","a"}));
        assertEquals(Map.of(), codingBat.wordCount(new String[]{}));
        assertEquals(Map.of("banana",2,"b",1),codingBat.wordCount(new String[]{"banana","banana","b"}));
    }
}