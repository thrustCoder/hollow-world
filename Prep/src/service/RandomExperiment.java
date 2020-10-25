package service;

import util.Printer;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by rpsin on 3/3/2017.
 */
public class RandomExperiment {

    public static void testZonedDateTime() {
        ZonedDateTime lipsDBBirthday = ZonedDateTime.parse("2017-02-24T00:05:00Z[America/Los_Angeles]");
        ZonedDateTime later = ZonedDateTime.parse("2017-02-25T00:00:00Z[America/Los_Angeles]");
        ZonedDateTime before = ZonedDateTime.parse("2017-02-24T00:00:00Z[America/Los_Angeles]");

        ZonedDateTime before2 = ZonedDateTime.parse("2017-02-24T00:10:00Z[Europe/Paris]");

        if (later.toInstant().isAfter(lipsDBBirthday.toInstant())) {
            Printer.println("Yes!");
        } else {
            Printer.println("??");
        }

        if (before.toInstant().isBefore(lipsDBBirthday.toInstant())) {
            Printer.println("Yes!");
        } else {
            Printer.println("??");
        }

        if (before2.toInstant().isBefore(lipsDBBirthday.toInstant())) {
            Printer.println("Yes!");
        } else {
            Printer.println("??");
        }
    }

    public static void testStreamInBooleanArray() {
        Boolean[] b = new Boolean[] {true, false, false, true};
        Printer.print(
                Arrays.stream(b)
                        .filter(x -> x == true)
                        .count());
    }

    public static void testAnyMatchInEmptyList() {
        List<String> myList = Arrays.asList();

        // prints false
        Printer.print(myList.stream()
                .anyMatch(ele -> ele.equals("d")));
    }

    public static void testNullValueInForLoop() {
        List<String> myList = null;

        // throws NPE
        for (String a : myList) {
            Printer.print("inside for loop, element = " + a);
        }
        Printer.print("Outside for loop");
    }

    public static void testDoubleComparison() {
        Double a = 0.000001;

        // prints true
        Printer.print(a > 0);
    }

    public static void testStringComparison() {
        String a = null;

        // prints false
        Printer.print("a".equals(a));
    }

    public static void testSubstringEdgeCase() {
        String s = "a";

        // prints "a"
        Printer.println(s.substring(0));

        // prints ""
        Printer.println(s.substring(1));

        // StringIndexOutOfBoundsException
        Printer.println(s.substring(2));
    }

    public static void nonMutatingSortInList() {
        List<Integer> originalList = Arrays.asList(4,2,4,5,6,1);

        List<Integer> copyList = new ArrayList<>(originalList);

        Collections.sort(copyList);

        Printer.println("original = " + originalList);
        Printer.println("copy = " + copyList);
    }

    public static void nonMutatingSortInArray() {
        int[] originalList = {4,2,4,5,6,1};

        int[] copyList = Arrays.copyOf(originalList, originalList.length);

        Arrays.sort(copyList);

        for (int i : originalList) {
            Printer.print(i + " ");
        }

        Printer.println();

        for (int i : copyList) {
            Printer.print(i + " ");
        }
    }

    public static void testNullBoolean() {
        Boolean a = null;
        // throws NPE
        if (a) {
            Printer.print("inside if block");
        }
    }

    public static void testNullList() {
        // throws NPE
        Printer.print(Arrays.asList(null));
    }

    public static void testIntegerToLongConversion() {
        Integer a = 10;
        Printer.print(testIntegerToLongConversionHelper(a.longValue()));
    }

    private static boolean testIntegerToLongConversionHelper(long a) {
        return a == 10l;
    }
}
