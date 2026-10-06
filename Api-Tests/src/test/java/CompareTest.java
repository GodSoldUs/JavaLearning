import Compares.CompareAbs;
import Compares.CompareSudo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CompareTest {
    @Test
    void absSortTest() {
        List<Integer> actual = Arrays.asList(111, -999, 555, -362);
        actual.sort(new CompareAbs());
        actual.forEach(System.out::println);
    }

    @Test
    void haveSudoTest() {
        List<String> linux = Arrays.asList("sudo apt", "ls", "update", "sudo su");
        linux.sort(new CompareSudo());
        linux.forEach(System.out::println);

    }

}
