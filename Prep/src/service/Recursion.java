package service;

import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rpsin on 11/5/2016.
 */
public class Recursion {
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
