package service;

import util.Printer;

/**
 * This was asked to me in my MSFT interview.
 * Given a money amount in decimal, return its text representation;
 * like we would while writing a check.
 * E.g. 583 should return five hundred eighty three dollars.
 *
 * Solution inspired by: https://www.baeldung.com/java-money-into-words
 */
public class ConvertMoneyDigitsToWordsSolution {
    private static String[] ones = {
        "", "one", "two", "three", "four",
        "five", "six", "seven", "eight",
        "nine", "ten", "eleven", "twelve",
        "thirteen", "fourteen", "fifteen",
        "sixteen", "seventeen", "eighteen",
        "nineteen"
    };
    private static String[] tens = {
        "",          // 0
        "",          // 1
        "twenty",    // 2
        "thirty",    // 3
        "forty",     // 4
        "fifty",     // 5
        "sixty",     // 6
        "seventy",   // 7
        "eighty",    // 8
        "ninety"     // 9
    };

    public static void invoke() {
        // Prints thirty four dollars and forty five cents
        Printer.println(getMoneyIntoWords(34.45));

        // Prints eight thousand four hundred twenty five dollars and sixty nine cents
        Printer.println(getMoneyIntoWords(8425.69));

        // Prints one cent
        Printer.println(getMoneyIntoWords(0.01));

        // Prints nine hundred fifty nine cents
        Printer.println(getMoneyIntoWords(0.959));

        // Prints zero dollars
        Printer.println(getMoneyIntoWords(0));

        // Prints thirty three million three hundred forty eight thousand nine hundred seventy eight dollars
        Printer.println(getMoneyIntoWords(33_348_978));

        // Prints nine hundred twenty four dollars and six cents
        Printer.println(getMoneyIntoWords(924.6));
    }

    private static String convert(long n) {
        String result = null;

        if (n < 20) {
            result = ones[(int) n];

        } else if (n < 100) {
            long quotient = n / 10;
            long remainder = n % 10;

            result = tens[(int) quotient] + (remainder == 0 ? "" : " ") + ones[(int) remainder];

        } else if (n < 1_000) {
            long quotient = n / 100;
            long remainder = n % 100;

            return convert(quotient) + " hundred" + (remainder == 0 ? "" : " ") + convert(remainder);

        } else if (n < 1_000_000) {
            long quotient = n / 1000;
            long remainder = n % 1000;

            result = convert(quotient) + " thousand" + (remainder == 0 ? "" : " ") + convert(remainder);

        } else if (n < 1_000_000_000) {
            long quotient = n / 1_000_000;
            long remainder = n % 1_000_000;

            result = convert(quotient) + " million" + (remainder == 0 ? "" : " ") + convert(remainder);
        }

        return result;
    }

    private static String getMoneyIntoWords(double money) {
        // input validation
        if (money < 0D) {
            throw new IllegalArgumentException("Money value should not be negative");
        }
        if (money == 0D) {
            return "zero dollars";
        }

        long dollars = (long) money;

        // if you were asked to round up the decimal part to 2 digits, this is how you'd get the cents part.
        // long cents = Math.round((money - dollars) * 100);

        // otherwise, if you were asked to keep the decimal part as is, no matter how long it is,
        // you'd do like below.
        // Here, if (money - dollars) is not zero, it means there is a decimal part.
        // So we split money on decimal point and get the second half to get the cents.
        long cents = Long.parseLong(money - dollars == 0 ? "0" : String.valueOf(money).split("\\.")[1]);

        String dollarsPart = "";
        if (dollars > 0) {
            dollarsPart += convert(dollars) + " dollar" + (dollars == 1 ? "" : "s");
        }

        String centsPart = "";
        if (cents > 0) {
            if (!dollarsPart.isEmpty()) {
                centsPart = " and ";
            }
            centsPart += convert(cents) + " cent" + (cents == 1 ? "" : "s");
        }

        return dollarsPart + centsPart;
    }
}
