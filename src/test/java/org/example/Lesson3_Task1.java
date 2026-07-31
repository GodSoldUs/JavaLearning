package org.example;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@Tag("Lesson3")
public class Lesson3_Task1 {
    @Test
    @DisplayName("UpperCaseTest")
    void makeCapitalLettersTest() {
        assertThat(Main.makeCapitalLetters("hello")).isEqualTo("HELLO");
    }

    @Test
    @DisplayName("listReduceTest")
    void reduceTest() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> expectedArr = new ArrayList<>(Arrays.asList(1, 2, 3));
        assertThat(Main.reduce(arr, 3)).isEqualTo(expectedArr);
    }

    @Test
    @DisplayName("stringContainsTest")
    void isStringContainsTest() {
        assertThat(Main.isStringContains("Hello, world!", "world")).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bob", "Staysy", "Daniel", ""})
    @DisplayName("greetingTest")
    void greetingTest(String name) {
        assertThat(Main.greeting(name)).contains(name);
    }
}


