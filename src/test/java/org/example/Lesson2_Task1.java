package org.example;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;
import java.util.stream.IntStream;



class Lesson2_Task1 {

    @BeforeEach
    void start() {
        System.out.println("========================\n" + "Test method start");
    }

    @AfterEach
    void finish() {
        System.out.println("Test method end\n" + "========================");
    }

    static IntStream randomScores() {
        return new Random().ints(3, 1, 101);
    }


    @Test
    void isEvenTest() {
        int value = new Random().nextInt(1, 101);
        System.out.println("isEvenTest: value = " + value +
                ", result: " + Main.isEven(value));
    }


    @RepeatedTest(value = 20, name = "Повтор {currentRepetition} из {totalRepetitions}")
    void checkAccessTest() {
        int value = new Random().nextInt(100);
        System.out.println("checkAccessTest: value = " + value +
                ", result: " + Main.checkAccess(value));
    }


    @ParameterizedTest
    @MethodSource("randomScores")
    void getGradeTest(int score) {
        String grade = Main.getGrade(score);
        System.out.println("getGradeTest:" +
                "Grade - " + grade + ", score - " + score);
    }

}

