import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class StreamTest {
    @Test
    void streamTestMoreThanFive() {
        List<Integer> numbers = Arrays.asList(1, 4, 5, 6, 8, 1, 400, 23, 1);
        Stream<Integer> streamNums = numbers.stream()
                .filter(s -> s > 5);
        streamNums.forEach(System.out::println);
    }

    @Test
    void streamStringToInt() {
        List<String> strArray = Arrays.asList("2", "4", "Hello", "0", "Two", "One", "999");
        Stream<Integer> strInt = Arrays.stream(strArray.stream()
                .filter(s -> s.matches("\\d+"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new));

        strInt.forEach(System.out::println);
    }

}