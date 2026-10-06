package Compares;
import java.util.Comparator;

public class CompareAbs implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(Math.abs(o2), Math.abs(o1));
    }
}
