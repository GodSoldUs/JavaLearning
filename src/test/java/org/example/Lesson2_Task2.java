package org.example;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class Lesson2_Task2 {

    private static String[] arrayWithBug(Random random) {
        int size = random.nextInt(10) + 1;
        String[] arr = new String[size];
        int bugIndex = random.nextInt(size);
        for (int i = 0; i < size; i++) {
            if (i == bugIndex) {
                arr[i] = random.nextBoolean() ? "Bug" : "bug";
            } else {
                arr[i] = "word" + i;
            }
        }
        return arr;
    }

    private static String[] arrayWithoutBug(Random random) {
        int size = random.nextInt(10) + 1;
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = "word" + i;
        }
        return arr;
    }

    private static int[] randomIntArray(Random random) {
        int size = random.nextInt(10) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(-101, 101);
        }
        return arr;
    }

    @Tag("Lesson3")
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100, -2, 8, 44, 102, 16})
    void isEvenTest(int num) {
        assertThat(Main.isEven(num)).isTrue();
    }

    @Tag("Lesson3")
    @ParameterizedTest
    @CsvFileSource(resources = "/checkAccess.csv", delimiter = ';')
    void checkAccessTest(int age, String decision) {
        assertThat(Main.checkAccess(age)).isEqualTo(decision);
    }

    @Tag("Lesson3")
    @RepeatedTest(value = 10)
    void isPositiveTest() {
        int n = new Random().nextInt(1, 100);
        assertThat(Main.isPositive(n)).isTrue();

    }

    @Tag("Lesson3")
    @ParameterizedTest
    @CsvFileSource(resources = "/getGradeData.csv", numLinesToSkip = 1, delimiter = ';')
    void getGradeTest(int score, String expectedGrade) {
        assertThat(Main.getGrade(score)).isEqualTo(expectedGrade);
    }

    @Tag("Lesson3")
    @RepeatedTest(10)
    void blastOffTest() {
        var n = new Random().nextInt(1, 11);
        assertThat(Main.blastOff(n)).contains(String.valueOf(n), "Поехали!");

    }

    @Tag("Lesson3")
    @RepeatedTest(10)
    void sumToNTest() {
        int n = new Random().nextInt(100);
        assertThat(Main.sumToN(n)).isGreaterThanOrEqualTo(n);
    }

    @Tag("Lesson3")
    @RepeatedTest(10)
    void hasBugTest() {
        Random random = new Random();
        String[] arrayWithBug = arrayWithBug(random);
        String[] arrayWithoutBug = arrayWithoutBug(random);
        assertThat(Main.hasBug(arrayWithBug)).isTrue();
        assertThat(Main.hasBug(arrayWithoutBug)).isFalse();

    }

    @Tag("Lesson3")
    @RepeatedTest(10)
    void getEvenInRangeTest() {
        assertThat(Main.getEvenInRange(2, 6)).isEqualTo("2 4 6");

    }

    @Tag("Lesson3")
    @RepeatedTest(10)
    void findMaxTest() {
        Random random = new Random();
        int[] arr = randomIntArray(random);
        assertThat(Main.findMax(arr)).isEqualTo(Arrays.stream(arr).max().getAsInt());
    }

    @Tag("Lesson3")
    @RepeatedTest(10)
    void reverseTest() {
        Random random = new Random();
        String[] arr = arrayWithBug(random);
        String[] expected = arr.clone();
        Collections.reverse(Arrays.asList(expected));
        assertThat(Main.reverse(arr)).containsExactly(expected);

    }

    @Tag("Lesson3")
    @RepeatedTest(10)
    void calcAverageTest() {
        Random random = new Random();
        int[] randomArray = randomIntArray(random);
        List<Integer> list = Arrays.stream(randomArray)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        assertThat(Main.calcAverage(list)).isEqualTo(list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
        );

    }

    @Tag("Lesson3")
    @RepeatedTest(5)
    void removeSpecificNameTest() {
        Random random = new Random();
        List<String> list = new ArrayList<>(Arrays.asList(arrayWithBug(random)));
        List<String> expected = new ArrayList<>(list);
        expected.remove("bug");
        assertThat(Main.removeSpecificName(list, "bug")).containsExactlyElementsOf(expected);
    }

}