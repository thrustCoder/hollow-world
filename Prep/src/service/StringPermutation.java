package service;

import java.util.HashSet;
import java.util.Set;

import util.Printer;

public class StringPermutation {

    /**
     * Prints permutations of a string.
     * We use Set since we are assuming that only *unique* permutations are needed.
     * For non unique, feel free to replace Set with ArrayList.
     */
    public static void printPermutations() {
        final String str = "abc";

        final Set<String> permutations = getPermutations(str);

        Printer.println("Permutations = " + permutations);
        if (permutations != null) {
            Printer.println("Count = " + permutations.size());
        }
    }

    /**
     * Recursive method to print permutations of a string.
     * O(n) = n!  (cannot do better than this)
     * @param str
     * @return
     */
    private static Set<String> getPermutations(final String str) {

        // validate str
        if (str == null) {
            return null;
        }

        final Set<String> permutations = new HashSet<>();

        // base case
        if (str.isEmpty()) {
            permutations.add("");

        } else {

            // recursively call this method on substring sans first character
            final char firstChar = str.charAt(0);
            final String sansFirstChar = str.substring(1);
            final Set<String> permuteStrings = getPermutations(sansFirstChar);

            for (final String permuteString : permuteStrings) {

                // insert firstChar at every ith position of permuteString
                for (int i = 0; i <= permuteString.length(); i++) {
                    permutations.add(insertCharAt(permuteString, i, firstChar));
                }

            }
        }

        return permutations;
    }

    private static String insertCharAt(final String str, final int index, final char c) {
        final StringBuilder insertedString = new StringBuilder();

        // first half of str
        insertedString.append(str.substring(0, index));

        // insert char
        insertedString.append(c);

        // second half of str
        insertedString.append(str.substring(index));

        return insertedString.toString();
    }
}
