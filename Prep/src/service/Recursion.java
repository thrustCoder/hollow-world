package service;

import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rpsin on 11/5/2016.
 */
public class Recursion {
    // LinkedIn phone screen Q
    // Find a^b
    // 3^1 = 3
    // 3^4 = (3^2) * (3^2)
    // 3^5 = (3^2) * (3^2) * 3
    // 3^(-4) = 1/(3^4)
    public static void prepPow() {
        Printer.println(String.valueOf(powWithRecursion(3, 2)));
        Printer.println(String.valueOf(powWithRecursion(3, 3)));
        Printer.println(String.valueOf(powWithRecursion(3, 0)));
        Printer.println(String.valueOf(powWithRecursion(0, 2)));
        Printer.println(String.valueOf(powWithRecursion(0, 0)));
        Printer.println("" + powWithRecursion(2, -1));
        Printer.println(String.valueOf(powWithRecursion(2, -2)));
        Printer.println(String.valueOf(powWithRecursion(0, -2)));
    }

    public static double powWithRecursion(double a, int n) {
        final double pow = powRecursiveUnit(a, Math.abs(n));

        if (n >= 0) {
            return pow;
        } else {
            if (a == 0) {
                throw new IllegalArgumentException("Denominator is 0");
            }
            return 1/pow;
        }
    }

    public static double powRecursiveUnit(double a, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }

        double powBy2;
        if (n % 2 == 0) {
            powBy2 = powRecursiveUnit(a, n/2);
            return powBy2 * powBy2;
        } else {
            powBy2 = powRecursiveUnit(a, (n - 1)/2);
            return powBy2 * powBy2 * a;
        }
    }

    public static double powWithIteration(double a, int n) {
        double p = 1;
        for (int i = 1; i <= Math.abs(n); i++) {
            p *= a;
        }

        if (n >= 0) {
            return p;
        } else {
            return 1/p;
        }
    }

    // Q DSA made easy book. Page 383
    // Find element with value same as index
    public static void prepIndexSearcher() {
        List<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(-3, -2, 0, 1, 2, 3, 6, 66));

        Printer.println("Index: " + indexSearcher(a, 0, a.size()-1));
    }

    public static int indexSearcher(List<Integer> a, int l, int r) {
        int mid = (r-l)/2 + l;

        if (a.get(mid) == mid) {
            return mid;
        }

        if (l > r) {
            return 0;
        }

        if (a.get(mid) < mid) {
            return indexSearcher(a, mid+1, r);
        } else {
            return indexSearcher(a, 0, mid-1);
        }
    }
}
