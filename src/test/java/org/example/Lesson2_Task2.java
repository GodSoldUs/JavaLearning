package org.example;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Lesson2_Task2 {

    static int randomInt() {
        return new Random().nextInt(1,20);
    }

    @RepeatedTest(value = 3)
    void isEvenTest() {
        String res = (Main.isEven(5))? "TEST PASSED": "TEST FAILED";
        System.out.println(res);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkAccess.csv")
    void checkAccessTest() {
        assertEquals("Allowed", Main.checkAccess(19));
        assertEquals("Denied", Main.checkAccess(18));
    }

    @RepeatedTest(value = 3)
    void isPositiveTest() {
        int n = new Random().nextInt(0,100);
        String res = (Main.isEven(n))? "TEST PASSED": "TEST FAILED";
        System.out.println(res);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/getGradeData.csv", numLinesToSkip = 1, delimiter = ';')
    void getGradeTest(int score, String expectedGrade) {
        String res = (Main.getGrade(score).equals(expectedGrade))? "TEST PASSED": "TEST FAILED";
        System.out.println(res);
    }

    @RepeatedTest(5)
    void blastOffTest() {
        var n = new Random().nextInt(6);
        String res = (Main.blastOff(n).equals("5 4 3 2 1 Поехали!"))? "TEST PASSED": "TEST FAILED";
        System.out.println(res);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void sumToNTest(int value) {
        String res = (Main.sumToN(value) == 5)? "TEST PASSED": "TEST FAILED";
        System.out.println(res);
    }

    @Test
    void hasBugTest() {
        String res = (Main.hasBug(new String[]{"ok", "BUG", "done"}))? "TEST PASSED": "TEST FAILED";
        System.out.println(res);
    }

    @Test
    void getEvenInRangeTest() {
        String res = (Main.getEvenInRange(2, 6).equals("2 4 6")) ? "TEST PASSED" : "TEST FAILED";
        System.out.println(res);
    }

    @Test
    void findMaxTest() {
        String res = (Main.findMax(new int[]{1, 5, 9, 3}) == 9)? "TEST PASSED" : "TEST FAILED";
        System.out.println(res);
    }

    @Test
    void reverseTest() {
        String res = (Arrays.equals(
                Main.reverse(new String[]{"One", "Two", "Zero"}),
                new String[]{"One", "Two", "Zero"}))?"TEST PASSED" : "TEST FAILED";
        System.out.println(res);
    }

    @Test
    void calcAverageTest() {
        String res = (Main.calcAverage(List.of(1, 2, 3, 4, 5)) == 3)?"TEST PASSED" : "TEST FAILED";
        System.out.println(res);
    }

    @RepeatedTest(5)
    void removeSpecificNameTest() {
        List<String> result = Main.removeSpecificName(
                Arrays.asList("Ann", "Bob", "Ann"),
                "Ann"
        );
        String res = (List.of("Bob", "Ann").equals(result))?"TEST PASSED" : "TEST FAILED";
        System.out.println(res);
    }
}