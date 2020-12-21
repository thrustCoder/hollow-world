package service;

import java.util.Stack;

import util.Printer;

/**
 * Created by rpsin on 11/16/2016.
 */
public class StringManipulation {
    // MSFT OTS
    // Longest common substring
    public static void lcs() {
        String str1 = "abcbdab";
        String str2 = "bdbcbdda";

        int start = 0;
        int max = 0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                int x = 0;
                while ((i+x < str1.length()) && (j+x < str2.length()) && (str1.charAt(i+x) == str2.charAt(j+x))) {
                    x++;
                }
                if (x > max) {
                    start = i;
                    max = x;
                }
            }
        }

        Printer.println(str1.substring(start, (start + max)));
    }

    // Fill " " with "%20"
    // Page 175 CCI Book
    public static void replaceSpaces() {
        String str1 = " ab cd ";
        char[] str = str1.toCharArray();

        int countSpaces = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                countSpaces++;
            }
        }

        int newLength = str.length + countSpaces*2;
        char[] newStr = new char[newLength];

        int newIndex = newLength - 1;

        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                newStr[newIndex] = '0';
                newStr[newIndex-1] = '2';
                newStr[newIndex-2] = '%';
                newIndex -= 3;
            } else {
                newStr[newIndex--] = str[i];
            }
        }

        Printer.println(new String(newStr));
    }

    // Recursively remove all adjacent duplicate from the given String
    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
    public static void removeAdjDup()
    {
        // Prints acac
        // String S = "acaaabbbacdddd";

        // Prints lop
        // String S = "ocvvcolop";

        // Prints MIPIE,
        // Well ideally, it should print MPIE, per https://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
        // But I think this is an edge case that if coded for, will make the solution more complicated (with recursive calls etc.)
        // Also, it depends on what the interviewer is asking for.
        // String S = "MISSISSIPIE";

        // Prints ca
        String S = "abbaca";

        Printer.println("Input = " + S);

        // input validation
        if (S == null || S.length() <= 1) {
            Printer.println("Output = Input = " + S);
            return;
        }

        final char[] charStr = S.toCharArray();
        final int strLength = charStr.length;

        final Stack<Character> nonDupChars = new Stack<>();

        for (int i = 0; i < strLength; i ++) {
            final char currChar = charStr[i];

            if (!nonDupChars.isEmpty() && nonDupChars.peek() == currChar) {
                nonDupChars.pop();

                while (i + 1 < strLength && currChar == charStr[i + 1]) {
                    i ++;
                }
            } else {
                nonDupChars.push(currChar);
            }
        }

        final StringBuilder resultSb = new StringBuilder();
        for (char c : nonDupChars) {
            resultSb.append(c);
        }

        String result = resultSb.toString();

        Printer.println("Output = " + result);
    }
}
