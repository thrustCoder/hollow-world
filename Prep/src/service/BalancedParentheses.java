package service;

import java.util.ArrayList;
import java.util.List;

import util.Printer;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * https://leetcode.com/problems/generate-parentheses/
 */
public class BalancedParentheses {

    private static final String OPEN_PAREN = "(";
    private static final String CLOSE_PAREN = ")";
    private static final String EMPTY_STRING = "";

    private static List<String> allParens;

    public static void generateBalancedParens() {
        Printer.print(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        allParens = new ArrayList<>();
        recursiveGenParentheses(EMPTY_STRING, 0, 0, n);
        return allParens;
    }

    private static void recursiveGenParentheses(final String currentParenString, final int openCount, final int closeCount, final int n) {
        // base case
        if (closeCount == n) {
            // done generating one combination; add it to the list
            allParens.add(currentParenString);
            return;
        }

        if (openCount < n) {
            // increment openCount
            recursiveGenParentheses(currentParenString + OPEN_PAREN, openCount + 1, closeCount, n);
        }

        if (closeCount < openCount) {
            // increment closeCount
            recursiveGenParentheses(currentParenString + CLOSE_PAREN, openCount, closeCount + 1, n);
        }
    }
}