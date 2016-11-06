package service;

import model.GrowthDirection;
import model.GrowthMetric;
import util.Printer;

import java.util.*;

/**
 * Created by rpsin on 10/12/2016.
 */
public class NumberMagic
{
    // TODO: Fix this, not working
    // MSFT Tech phone screen problem, fill a grid like snake
    public static void snakeFillGrid() {
        int[] a = new int[16];
        for (int i = 0; i < 16; i++) {
            a[i] = i;
            Printer.print(" " + a[i]);
        }
        int[][] matrix = new int[4][4];

        int i = 0;
        int j = 0;
        int k = 0;
        int computeCount = 1;
        GrowthMetric growthMetric = GrowthMetric.ROW;
        GrowthDirection direction = GrowthDirection.FORWARD;

        Map<Integer, Map<Integer, Boolean>> filledIndexMap = new HashMap<>();
        while(k < 16) {
            if (!fillMapContainsIndices(filledIndexMap, i, j)) {
                matrix[i][j] = a[k];
                addToFilledMap(filledIndexMap, i, j);
            } else {
                growthMetric = computeGrowthMetric(filledIndexMap, i, j);
                if (computeCount % 2 == 0) {
                    if (direction == GrowthDirection.FORWARD) {
                        direction = GrowthDirection.BACKWARD;
                    } else {
                        direction = GrowthDirection.FORWARD;
                    }
                }
                computeCount++;
            }

            if (direction == GrowthDirection.FORWARD) {
                if (growthMetric == GrowthMetric.ROW) {
                    j++;
                } else {
                    i++;
                }
            } else {
                if (growthMetric == GrowthMetric.ROW) {
                    j--;
                } else {
                    i--;
                }
            }
            i = Math.abs(i) % 4;
            j = Math.abs(j) % 4;
            k++;
        }

        for (i = 0; i < 4; i++) {
            Printer.println("");
            for (j = 0; j < 4; j++) {
                Printer.print(" " + matrix[i][j]);
            }
        }
    }

    private static GrowthMetric computeGrowthMetric(Map<Integer, Map<Integer, Boolean>> fillMap, int i, int j) {
        if (fillMapContainsIndices(fillMap, i, j+1) || j+1 > 3) {
            return GrowthMetric.COLUMN;
        } else {
            return GrowthMetric.ROW;
        }
    }

    private static boolean fillMapContainsIndices(Map<Integer, Map<Integer, Boolean>> fillMap, int i, int j) {
        return (fillMap.containsKey(i) && fillMap.get(i).containsKey(j));
    }

    private static void addToFilledMap(Map<Integer, Map<Integer, Boolean>> fillMap, int i, int j) {
        if (fillMap.containsKey(i)) {
            Map<Integer, Boolean> map = fillMap.get(i);
            map.put(j, true);
        } else {
            Map<Integer, Boolean> map = new HashMap<>();
            map.put(j, true);

            fillMap.put(i, map);
        }
    }

    // MSFT interview Q on-site
    public static void clock180DegreeCount() {
        int hourHandPos = 0;
        int secondHandPos = 0;
        int count = 0;

        while (hourHandPos < 60) {
            if (is180Degree(hourHandPos, secondHandPos)) {
                Printer.println("Hour: " + hourHandPos/5 + " Second: " + secondHandPos);
                count++;
            }

            if (secondHandPos == 60) {
                secondHandPos = 0;
            } else {
                secondHandPos++;
            }
            if (secondHandPos % 12 == 0) {
                hourHandPos++;
            }
        }

        Printer.println("Number of time 180 degree occurs in a 24 hour period: " + count * 2);
    }

    private static boolean is180Degree(int hourHandPos, int secondHandPos) {
        return Math.abs(hourHandPos - secondHandPos) == 30;
    }

    public static void waysToSum() {
        // Career cup https://www.careercup.com/question?id=16230693
        // Ways to sum a set of numbers to 12
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(8, 4, 6, 2, 14));

        a.sort((o1, o2) -> o1 - o2);
        Printer.println("Sorted array: ");
        a.stream()
                .forEach(n-> {Printer.print(n + " ");});

        int index = a.size() - 1;
        Printer.println("\nNumber of ways: " + getNoOfWays(a, index, 12));
    }

    // ways(a, i, sum) = ways(a - a[i], i-1, sum +- a[i])
    public static int getNoOfWays(List<Integer> array, int index, int value) {
        if ((index < 0 || index >= array.size()) && value != 0) {
            return 0;
        } else if (value == 0) {
            return 1;
        }

        return getNoOfWays(array, index - 1, value)
                + getNoOfWays(array, index - 1, value - array.get(index));
    }

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
