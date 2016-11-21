package service;

import util.Printer;

/**
 * Created by rpsin on 11/17/2016.
 */
public class DynamicProgramming {
    // Longest common subsequence
    // Page 395 of DS made easy book
    // LCS(i, j): 0 if i=m or j=n
    // LCS(i, j): Max(LCS(i+1, j), LCS(i, j+1)) if X[i] != Y[j]
    // LCS(i, j): 1 + LCS(i+1, j+1) if X[i] = Y[j]
    public static void prepLCS() {
        char[] X = "abcbdab".toCharArray();
        char[] Y = "bdcaba".toCharArray();

        String lcs = lcs(X, Y);
        Printer.println("LCS: " + lcs);
    }

    public static String lcs(char[] X, char[] Y) {
        int[][] lcsTable = new int[X.length+1][Y.length+1];

        for (int i = 0; i <= X.length; i++) {
            lcsTable[i][0] = 0;
        }

        for (int j=0; j <= Y.length; j++) {
            lcsTable[0][j] = 0;
        }

        for (int i = 1; i <= X.length; i++) {
            for (int j = 1; j <= Y.length; j++) {
                if (X[i-1] == Y[j-1]) {
                    lcsTable[i][j] = lcsTable[i-1][j-1] + 1;
                } else {
                    lcsTable[i][j] = Math.max(lcsTable[i-1][j], lcsTable[i][j-1]);
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for (int i = X.length, j = Y.length; i > 0 && j > 0;) {
            if (lcsTable[i-1][j] == lcsTable[i][j]) {
                i--;
            } else if (lcsTable[i][j-1] == lcsTable[i][j]) {
                j--;
            } else {
                str.append(Y[j-1]);
                i--;
                j--;
            }
        }

        return str.reverse().toString();
    }

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

            Printer.println("M[" + i + "] = " + M[i]);
            if (M[i] > maxSum) {
                maxSum = M[i];
            }
        }

        return maxSum;
    }
}
