package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void isEvenTest() {
        assertTrue(Main.isEven(4));
        assertFalse(Main.isEven(5));
    }

    @Test
    void checkAccessTest() {
        assertEquals("Allowed", Main.checkAccess(19));
        assertEquals("Denied", Main.checkAccess(18));
    }

    @Test
    void isPositiveTest() {
        assertTrue(Main.isPositive(0));
        assertFalse(Main.isPositive(-1));
    }

    @Test
    void getGradeTest() {
        
        assertEquals("E", Main.getGrade(10));
        assertEquals("D", Main.getGrade(30));
        assertEquals("C", Main.getGrade(50));
        assertEquals("B", Main.getGrade(70));
        assertEquals("A", Main.getGrade(90));
    }

    @Test
    void blastOffTest() {
        assertEquals("5 4 3 2 1 Поехали!", Main.blastOff(5));
    }

    @Test
    void sumToNTest() {
        assertEquals(15, Main.sumToN(5));
    }

    @Test
    void hasBugTest() {
        assertTrue(Main.hasBug(new String[]{"ok", "BUG", "done"}));
        assertFalse(Main.hasBug(new String[]{"ok", "fine"}));
    }

    @Test
    void getEvenInRangeTest() {
        assertEquals("2 4 6", Main.getEvenInRange(2, 6));
    }

    @Test
    void findMaxTest() {
        assertEquals(9, Main.findMax(new int[]{1, 5, 9, 3}));
    }

    @Test
    void reverseTest() {
        assertArrayEquals(
                new String[]{"Zero", "Two", "One"},
                Main.reverse(new String[]{"One", "Two", "Zero"})
        );
    }

    @Test
    void calcAverageTest() {
        assertEquals(3, Main.calcAverage(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void removeSpecificNameTest() {
        List<String> result = Main.removeSpecificName(
                Arrays.asList("Ann", "Bob", "Ann"),
                "Ann"
        );
        assertEquals(List.of("Bob", "Ann"), result);
    }
}