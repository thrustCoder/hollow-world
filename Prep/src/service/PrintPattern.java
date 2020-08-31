package service;

import util.Printer;

public class PrintPattern {

    public static void printPattern() {
        int n = 5;
        printIncreasingNumbersLeftTriangle(n);
    }

    /*

     *
     * *
     * * *
     * * * *
     * * * * *

     */
    private static void printStarsLeftTriangle(final int n) {

        // outer loop - for going over each of the n rows.
        for (int i = 0; i < n; i ++) {

            // inner loop - for printing the stars.
            for (int j = 0; j <= i; j ++) {
                Printer.print("* ");
            }

            Printer.println();
        }
    }

    /*

    1
    1 2
    1 2 3
    1 2 3 4
    1 2 3 4 5

     */
    private static void printNumbersLeftTriangle(final int n) {

        // outer loop - for going over each of the n rows.
        for (int i = 0; i < n; i ++) {

            // reinitializing num at the begininng of each row.
            int num = 1;

            // inner loop - for printing the number + space.
            for (int j = 0; j <= i; j ++) {
                Printer.print(num + " ");
                num ++;
            }

            Printer.println();
        }
    }

    /*

    1
    2 3
    4 5 6
    7 8 9 10
    11 12 13 14 15

     */
    private static void printIncreasingNumbersLeftTriangle(final int n) {

        // global num visible to both loops.
        int num = 1;

        // outer loop - for going over each of the n rows.
        for (int i = 0; i < n; i ++) {

            // inner loop - for printing the number + space.
            for (int j = 0; j <= i; j ++) {
                Printer.print(num + " ");
                num ++;
            }

            Printer.println();
        }
    }

    /*

            *
          * *
        * * *
      * * * *
    * * * * *

     */
    private static void printStarsRightTriangle(final int n) {

        // outer loop - for going over each of the n rows.
        for (int i = 0; i < n; i ++) {

            // First inner loop - Print *left* spaces for ith row.
            // e.g. if n = 5, stop at index 3, since we need to print the star at index 4.
            for (int j = 0; j < (n - i - 1); j ++) {
                // printing 2 spaces for displaying correctly.
                Printer.print("  ");
            }

            // Second inner loop - Print stars for ith row.
            for (int j = 0; j <= i; j ++) {
                Printer.print("* ");
            }

            Printer.println();
        }
    }

    /*

            *
           * *
          * * *
         * * * *
        * * * * *

     */
    private static void printStarsTriangle(final int n) {

        // outer loop - for going over each of the n rows.
        for (int i = 0; i < n; i ++) {

            // First inner loop - Print *left* spaces for ith row.
            // e.g. if n = 3, stop at index 1, since we need to print the star at index 2.
            for (int j = 0; j < (n - i - 1); j ++) {
                // printing only 1 spaces for displaying correctly.
                Printer.print(" ");
            }

            // Second inner loop - Print stars for ith row.
            for (int j = 0; j <= i; j ++) {
                Printer.print("* ");
            }

            Printer.println();
        }
    }
}
