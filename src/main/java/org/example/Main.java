package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static String checkAccess(int age) {
        return (age > 18)? "Allowed" : "Denied";
    }

    public static boolean isPositive(int n) {
        return (n >= 0)? true : false;  //same  n >= 10;
    }

    public static String getGrade(int score) {
        if (score <= 20) {
            return "E";
        } else if (score <= 40) {
            return "D";
        } else if (score <= 60) {
            return "C";
        } else if (score <= 80) {
            return "B";
        } else {
            return "A";
        }
    }

    public static String blastOff(int start) {
        String res = "";
        for (int i = start; i >= 1; i--) {
            res += i + " ";
        }
        res += "Поехали!";
        return res;
    }

    public static int sumToN(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += i;
        }
        return res;
    }

    public static boolean hasBug(String[] messages) {
        for (String message : messages) {
            if ("Bug".equalsIgnoreCase(message)) {
                return true;
            }
        }
        return false;
    }

    public static String getEvenInRange(int start, int end) {
        String res = "";
        for (int n = start; n <= end; n++) {
            if (n % 2 == 0) {
                res += n + " ";
            }

        }
        return res.trim();
    }

    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Пустой массив");
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static String[] reverse(String[] arr) {
        String[] newArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[arr.length - 1 - i];
        }
        return newArr;
    }

    public static int calcAverage(List<Integer> list) {
        int sum = 0;
        for (int el : list) {
            sum += el;
        }
        return sum/list.size();
    }

    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {
        List<String> res = new ArrayList<>(list);
        res.remove(nameToRemove);
        return res;
    }














    public static void main(String[] args) {
        System.out.println(getEvenInRange(2, 6));
    }
}