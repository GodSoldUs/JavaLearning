package Compares;

import java.util.Comparator;

public class CompareSudo implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        boolean haveSudo1 = o1.contains("sudo");
        boolean haveSudo2 = o2.contains("sudo");

        if (haveSudo1 && !haveSudo2) {
            return -1;
        }

        if (!haveSudo1 && haveSudo2) {
            return 1;
        }
        return 0;
    }
}
