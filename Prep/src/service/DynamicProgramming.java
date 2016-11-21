package service;

import util.Printer;

/**
 * Created by rpsin on 11/17/2016.
 */
public class DynamicProgramming {
    // Max sum withing NON contiguous sub array
    // Page 399 of DS made easy book
    public static void prepMaxSubsequenceSum() {
        int[] array = new int[] {1, 2, 3, -2, -3, 2};
        int sum = maxSubsequenceSum(array);

        Printer.println("Max sum: " + sum);
    }

    // M[i]: Max sum observed for sub subsequence until ith position
    // M[i] = Max(A[i] + M[i-2], M[i-1]) if i>1
    // M[i] = Max(A[1], A[0]) if i=1
    // M[i] = A[0] if i=0
    public static int maxSubsequenceSum(int[] array) {
        int[] M = new int[array.length];
        int maxSum = 0;

        for (int i=0; i < array.length; i++) {
            if (i == 0) {
                M[i] = array[0];
            } else if (i == 1) {
                M[i] = Math.max(array[0], array[1]);
            } else {
                M[i] = Math.max(array[i] + M[i-2], M[i-1]);
            }

            if (M[i] > maxSum) {
                maxSum = M[i];
            }
        }

        for (int i = 0; i < array.length; i++) {
            Printer.println("M[" + i + "] = " + M[i]);
        }

        return maxSum;
    }
}
