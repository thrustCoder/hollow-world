package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Printer;

public class ReverseWordsOfSentence {
    private static final String SPACE = " ";

    public static void invoke() {
        // Should print "eht yks si eulb."
        Printer.println(reverseWordCharactersOfSentence("the sky is blue."));

        // SHould print "yehT dah a ytrap-eat?!"
        Printer.println(reverseWordCharactersOfSentence("They had a tea-party?!"));

        // Should print "blue is sky the"
        Printer.println(reverseWordsOfSentence("the sky is blue"));
    }

    private static String reverseWordCharactersOfSentence(final String s) {
        // input validation
        if (s == null || s.isEmpty()) {
            return s;
        }

        // trim leading and trailing whitepsaces then
        // replace multiple whitespace characters with
        // single whitespace chracter
        final String str = s.trim().replaceAll("\\s+", SPACE);
        final String[] components = str.split(SPACE);
        final int componentsLen = components.length;
        final StringBuilder stringSb = new StringBuilder();

        for (int i = 0; i < componentsLen; i ++) {
            stringSb.append(punctAwareStringReversal(components[i]));
            if (i != componentsLen - 1) {
                stringSb.append(SPACE);
            }
        }

        return stringSb.toString();
    }

    // Only need to check for punctuation at the end.
    private static String punctAwareStringReversal(final String str) {
        int i = str.length() - 1;
        StringBuilder punctSb = new StringBuilder();
        while (i >= 0 && isPunct(str.charAt(i))) {
            punctSb.append(str.charAt(i));
            i --;
        }

        StringBuilder unreversedSb = new StringBuilder();
        if (i >= 0) {
            unreversedSb.append(str.substring(0, i + 1));
        }
        StringBuilder resultSb = new StringBuilder(unreversedSb.reverse());
        resultSb.append(punctSb.reverse());

        return resultSb.toString();
    }

    private static boolean isPunct(char ch) {
        Pattern pattern = Pattern.compile("\\p{Punct}");
        Matcher matcher = pattern.matcher(Character.toString(ch));
        return matcher.find();
    }

    private static String reverseWordsOfSentence(String s) {
        // input validation
        if (s == null || s.isEmpty()) {
            return s;
        }

        // trim leading and trailing whitepsaces then
        // replace multiple whitespace characters with
        // single whitespace chracter
        final String str = s.trim().replaceAll("\\s+", SPACE);
        final String[] components = str.split(SPACE);
        final int componentsLen = components.length;
        final StringBuilder stringSb = new StringBuilder();

        for (int i = componentsLen - 1; i >= 0; i --) {
            stringSb.append(components[i]);
            if (i != 0) {
                stringSb.append(SPACE);
            }
        }

        return stringSb.toString();
    }
}
