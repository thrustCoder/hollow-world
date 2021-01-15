package service;

import java.util.HashMap;
import java.util.Map;

import util.Printer;

/**
 * Return true if we can use all values to build sequences of 5 consecutive elements.
 *
 * [1,2,2,3,3,4,4,5,5,6] => true (we can build 2 sequences: 1,2,3,4,5 and 2,3,4,5,6)
 * [1,1,2,2,3,3,4,4,5,5,6] => false (we can build 2 sequences of 1,2,3,4,5, but there is one extra 6)
 * [1,2,3,4,5,6,7,8,9,5] => true (we can build 2 sequences: 1,2,3,4,5 and 5,6,7,8,9)
 *
 * This was asked to Olga in her Google interviews.
 * This soln is inspired by the soln to a similar problem in https://afteracademy.com/blog/longest-consecutive-sequence
 */
public class ConsecutiveElements {

    public static void invoke() {
        int[] arr1 = {1,2,2,3,3,4,4,5,5,6};
        // prints true
        Printer.println(doAllElementsMakeConsecutiveSequenceOfFiveElements(arr1));

        int[] arr2 = {1,1,2,2,3,3,4,4,5,5,6};
        // Prints false
        Printer.println(doAllElementsMakeConsecutiveSequenceOfFiveElements(arr2));

        int[] arr3 = {1,2,3,4,5,6,7,8,9,5};
        // Prints true
        Printer.println(doAllElementsMakeConsecutiveSequenceOfFiveElements(arr3));

        int[] arr4 = {1,5,6,7,8,9};
        // Prints false
        Printer.println(doAllElementsMakeConsecutiveSequenceOfFiveElements(arr4));

        int[] arr5 = {5,6,7,8,9,10};
        // Prints false
        Printer.println(doAllElementsMakeConsecutiveSequenceOfFiveElements(arr5));

        int[] arr6 = {4,2,1,5,4,2,3,6,3,5}; // just a jumbled version of test 1
        // Prints true
        Printer.println(doAllElementsMakeConsecutiveSequenceOfFiveElements(arr6));

        int[] arr7 = {1,1,2,2,3,3,4,5,5,6};
        // Prints false
        Printer.println(doAllElementsMakeConsecutiveSequenceOfFiveElements(arr7));
    }

    private static boolean doAllElementsMakeConsecutiveSequenceOfFiveElements(final int[] arr) {
        // input validation
        final int length = arr.length;
        if (length == 0) {
            return false;
        }

        final Map<Integer, Integer> elementCount = new HashMap<>();
        // populate the map
        for (int i = 0; i < length; i ++) {
            //elementCount.put(arr[i], elementCount.getOrDefault(arr[i], 0) + 1);

            // this is the same as above
            elementCount.compute(arr[i], (k, v) -> v == null ? 1 : v + 1);
        }

        int streaksOfFiveConsecutiveElements = 0;

        // iterate over the array
        for (int i = 0; i < length && !elementCount.isEmpty(); i ++) {
            if (elementCount.get(arr[i]) != null && elementCount.get(arr[i] - 1) == null) {
                // this is the first element in a potential consecutive streak
                int currStreak = 1;
                int currElement = arr[i];

                // repeat while streak is not 5 and next consecutive element exists
                while (currStreak < 5 && elementCount.get(currElement + 1) != null) {
                    reduceElementCount(elementCount, currElement);
                    currStreak ++;
                    currElement ++;
                }

                if (currStreak == 5) {
                    // reduce the count of the last processed element
                    reduceElementCount(elementCount, currElement);

                    streaksOfFiveConsecutiveElements ++;
                }
            }
        }
        return streaksOfFiveConsecutiveElements > 0 && elementCount.isEmpty();
    }

    private static void reduceElementCount(final Map<Integer, Integer> elementCount, final Integer element) {
        /*final Integer count = elementCount.get(element);

        if (count == null) {
            // shouldn't happen
            return;
        }

        if (count <= 1) {
            // remove element from map since it's exhausted its count
            elementCount.remove(element);
        } else {
            elementCount.put(element, count - 1);
        }*/

        // this is the same as above
        // Note computeIfPresent will remove the key too if the value is null
        elementCount.computeIfPresent(element, (k, v) -> v <= 1 ? null : v - 1);
    }
}
