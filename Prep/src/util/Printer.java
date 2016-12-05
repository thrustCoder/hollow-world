package util;

/**
 * Created by rpsin on 10/15/2016.
 */
public class Printer {
    public static void print(Object o) {
        System.out.print(o.toString());
    }

    public static void println(Object o) {
        System.out.println("\n" + o.toString());
    }
}
