package service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Stream;

import util.Printer;

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

    public static void testContainsInEmptyCollection() {
        Set<String> set = new HashSet<>();
        // Prints false
        Printer.print(set.contains(null));
    }

    public static void testOptionalNullableEmptyString() {
        String a = "a";

        // this is equivalent to String s = a;
        String s = Optional.ofNullable(a).orElse(null);

        // Prints a
        System.out.println(s);
    }

    public static void sortStack() {
        Stack<Integer> st = new Stack<>();
        st.add(6);
        st.add(4);
        st.add(1);
        st.add(5);
        st.add(6);

        Printer.println("Unsorted = " + st);
        Collections.sort(st);
        Printer.println("Sorted = " + st);
        Collections.reverse(st);
        Printer.println("Reverse Sorted = " + st);
    }

    public static void testTreeSetSorting() {
        int[] arr = {1, 94, 93, 1000, 5, 92, 78};

        final TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < arr.length; i ++) {
            treeSet.add(arr[i]);
        }

        // Prints sorted array
        // I think it takes O(nlgn) time - same as sorting an array
        // https://stackoverflow.com/questions/14379515/computational-complexity-of-treeset-methods-in-java
        Printer.print("TreeSet = " + treeSet);
    }

    private static boolean testIntegerToLongConversionHelper(long a) {
        return a == 10l;
    }

    public static void testListClone() {
        List<Integer> x = new ArrayList<>();
        x.add(1);

        Printer.println(((ArrayList<Integer>) x).clone());
        // or
        Printer.println(new ArrayList<>(x));
    }

    public static void testArrayClone() {
        Integer[] a = new Integer[]{1, 2, 3};
        Integer[] b = a.clone();

        for (Integer i : b) {
            Printer.print(i + " ");
        }
    }

    public static void testComparatorSort2dArr() {
        Integer[][] intervals = new Integer[][]{ {1,2}, {0,4}, {2, 3} };

        // sort based on first element
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        /*
            Prints:
            0 4
            1 2
            2 3
        */
        for (int i = 0; i < intervals.length; i ++) {
            for (int j = 0; j < intervals[i].length; j ++) {
                Printer.print(intervals[i][j] + " ");
            }
            Printer.println();
        }

        // Printer.println("Flattening 2d array:");
        // Prints 041223
        Arrays.stream(intervals).flatMap(s -> Arrays.stream(s)).forEach(x -> Printer.print(x));
        Printer.println("OR:");
        Arrays.stream(intervals).flatMap(Arrays::stream).forEach(Printer::print);

        // Creating priority queues with 2d array elements
        // This is a min heap whose nodes will represent an interval: so they will be of type Integer[]
        // and they are ordered by their end times (i.e. the second value of each interval)
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // Build heap by passing it a list of individual intervals (i.e. of type Integer[])
        pq.addAll(Arrays.asList(intervals));

        Printer.println();
        Printer.println("Printing built Priority Queue:");
        /*
            Prints:
            [1, 2]
            [2, 3]
            [0, 4]
        */
        while(!pq.isEmpty()) {
            final Integer[] minInterval = pq.remove();
            Printer.println("[" + minInterval[0] + ", " + minInterval[1] + "]");
        }
    }

    public static void testCreatingStringFromInt() {
        int a = 10;
        String str = String.valueOf(a);
        Printer.println(str);

        Integer b = a;
        String str2 = b.toString();
        Printer.println(str2);
    }

    public static void testListRemove() {
        List<String> x = new ArrayList<>();
        x.add("a");
        x.remove("a");

        List<Integer> y = new ArrayList<>();
        y.add(10);
        y.remove(0);  // this has to be index since your List itself is made of Integers

        Printer.println(x);
        Printer.println(y);
    }

    public static void testEmptyMapWithNullValue() {
        Map<Integer, Integer> testMap = new HashMap<>();

        // Prints true
        Printer.println(testMap.isEmpty());

        testMap.put(1, null);
        // Prints false
        Printer.println(testMap.isEmpty());

        testMap.put(1, 1);
        testMap.computeIfPresent(1, (k, v) -> v == 1 ? null : 2);

        // Prints true
        Printer.println(testMap.isEmpty());
    }

    public static void testCharacterUnicode() {
        // doesn't work with emojis though
        Character c = new Character('あ');
        Printer.print(c);
    }

    public static void testCreateStringFromChars() {
        char a = 'a';
        final String x = new String(Character.toChars(a));
        Printer.println(x);
    }

    public static void testLongDivision() {
        Long a = 10l;
        Long b = 3l;
        Long c = a / b;
        // Prints 3
        Printer.print(c);
    }

    public static void testIntegerDivision() {
        double a = 3.3;
        int b = 1;
        int c = (int) a/b;
        // Prints 3
        Printer.println(c);

        BigDecimal x = new BigDecimal(3.99);
        int y = x.intValue();
        // Prints 3
        Printer.println(y);
    }

    public static void testSplitWhitespaces() {
        String a = "  Hello world  ";
        String[] b = a.split(" ");
        for (String x : b) {
            // Prints " ", "Hello", "world", " "
            Printer.println(x);
        }
        // Prints 4
        Printer.println(b.length);

        // trims leading and trailing whitespaces
        String aTrim = a.trim();
        // Prints "Hello world"
        Printer.print(aTrim);

        // Prints " Hellow world "
        String aRegex = a.replaceAll("\\s+", " ");
        Printer.println(aRegex);
    }

    public static void testParseLong() {
        String a = new String();
        Printer.print(Long.parseLong(a));
    }
}
