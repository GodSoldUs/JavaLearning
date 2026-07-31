package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class Lesson3_Task2 {


    @Tag("Lesson3.2")
    @RepeatedTest(value = 3)
    void isEvenTest() {
        String res = (Main.isEven(5))? "TEST PASSED": "TEST FAILED";
        System.out.println(res);
    }



}
