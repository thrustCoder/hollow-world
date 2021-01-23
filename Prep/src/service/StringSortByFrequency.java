package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Printer;

public class StringSortByFrequency {
    public static void invoke() {
        String a  = "a";
        Printer.println(sortByCharFrequency(a));

        a  = "bab";
        Printer.println(sortByCharFrequency(a));
        Printer.println(sortByCharFrequencyEmojiAware(a));

        a  = "的不不";
        Printer.println(sortByCharFrequency(a));
        Printer.println(sortByCharFrequencyEmojiAware(a));

        a  = "abdea\uD83D\uDE28\uD83D\uDE00\uD83D\uDE00haabhe";
        Printer.println(sortByCharFrequency(a));
        Printer.println(sortByCharFrequencyEmojiAware(a));
    }

    /**
     * Handles all types of characters except emojis
     */
    private static String sortByCharFrequency(final String str) {

        // input validation
        if (str == null || str.isEmpty() || str.length() == 1) {
            // log.warn(“String {} is null or empty or single length”, str)
            return str;
        }

        final Map<Character, Integer> charFrequency = new HashMap<>();
        // populate the map
        for (char c : str.toCharArray()) {
            charFrequency.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }

        // max heap prior.q
        final PriorityQueue<Character> maxFrequencyCharsPQ =
                new PriorityQueue<>((a, b) -> charFrequency.get(b) - charFrequency.get(a));

        // populate the priority queue
        maxFrequencyCharsPQ.addAll(charFrequency.keySet());

        // create the sorted string
        final StringBuilder sortedSB = new StringBuilder();
        while (!maxFrequencyCharsPQ.isEmpty()) {
            final Character maxFreqChar = maxFrequencyCharsPQ.remove();
            final int freq = charFrequency.get(maxFreqChar);
            for (int i = 0; i < freq; i ++) {
                sortedSB.append(maxFreqChar);
            }
        }
        return sortedSB.toString();
    }

    /**
     * Handles emojis too
     */
    private static String sortByCharFrequencyEmojiAware(final String str) {

        // input validation
        if (str == null || str.isEmpty() || str.length() == 1) {
            // log.warn(“String {} is null or empty or single length”, str)
            return str;
        }

        final Map<String, Integer> charFrequency = new HashMap<>();
        // populate the map
        /*for (int i = 0; i < str.length(); i ++) {
            // get one character at at time
            //final String character = new String(Character.toChars(str.codePointAt(i)));
            final String character = str.substring(i, i + 1);
            charFrequency.compute(character, (k, v) -> v == null ? 1 : v + 1);
        }*/
        emojiAwareSplitter(str).stream()
                .forEach(character -> charFrequency.compute(character, (k, v) -> v == null ? 1 : v + 1));

        // max heap prior.q
        final PriorityQueue<String> maxFrequencyCharsPQ =
                new PriorityQueue<>((a, b) -> charFrequency.get(b) - charFrequency.get(a));

        // populate the priority queue
        maxFrequencyCharsPQ.addAll(charFrequency.keySet());

        // create the sorted string
        final StringBuilder sortedSB = new StringBuilder();
        while (!maxFrequencyCharsPQ.isEmpty()) {
            final String maxFreqChar = maxFrequencyCharsPQ.remove();
            final int freq = charFrequency.get(maxFreqChar);
            for (int i = 0; i < freq; i ++) {
                sortedSB.append(maxFreqChar);
            }
        }
        return sortedSB.toString();
    }

    /**
     * Uses a regex to match normal characters and emoji characters
     * and places them as separate elements on a List.
     *
     * The M in the regex stands for Mark
     * \P{M} would match a code point that is not a combining mark/surrogate
     * \p{M}* would match zero or more code points that are combining marks/surrogates
     * Notice P vs. p.
     *
     * So for normal characters, e.g. "a", the first code point is "a" which is
     * not a combining mark, so \P{M} matches it.
     * And for emoji characters, e.g. "😨", the first code point is "\uD83D"
     * which is not a combining mark, so \P{M} matches it
     * and the next code point is "\uDE28" whic IS a combining mark so
     * \p{M} matches it.
     *
     * Ref. https://www.regular-expressions.info/unicode.html and
     * https://stackoverflow.com/questions/59311482/splitting-a-string-that-contains-emojis
     */
    private static List<String> emojiAwareSplitter(final String str) {
        final List<String> resultList = new ArrayList<>();

        final Pattern pattern = Pattern.compile("\\P{M}\\p{M}*");
        final Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
            resultList.add(matcher.group());
        }
        return resultList;
    }
}
