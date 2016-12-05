package service;

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
}
