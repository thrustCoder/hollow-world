package service;

import util.Printer;

import java.util.ArrayList;

/**
 * Created by rpsin on 10/12/2016.
 */
public class NumberMagic
{
    // LinkedIn phone screen Q
    // Find a^b
    // 3^1 = 3
    // 3^4 = (3^2) * (3^2)
    // 3^5 = (3^2) * (3^2) * 3
    // 3^(-4) = 1/(3^4)
    public static void printPow() {
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
            if (pow == 0) {
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

    // Print factor multipliers without repitition
    // https://www.careercup.com/page?pid=linkedin-interview-questions
    public static void printPrimeFactors(int n)
    {
        System.out.println(n);
        ArrayList<Integer> factorsToPrune = new ArrayList<>((int)n/2);
        for (int i = 1; i < n/2; i++)
        {
            if (factorsToPrune.contains(i))
            {
                continue;
            }

            if (n % i == 0)
            {
                System.out.println(i + " * " + n/i);
                factorsToPrune.add(n/i);
            }
        }
    }
}
