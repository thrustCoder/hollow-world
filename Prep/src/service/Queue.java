package service;

import util.Printer;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by rpsin on 11/16/2016.
 */
public class Queue {
    public static void simpleOps() {
        java.util.Queue<Integer> q = new LinkedList<>();
        q.addAll(Arrays.asList(1, 2, 3, 4, 5));
        printQueue(q);

        Printer.println("\n Remove ");
        Printer.print("" + q.remove());
        printQueue(q);

        Printer.println("\n Element ");
        Printer.print("" + q.element());
        printQueue(q);

        Printer.println("\n Insert ");
        printQueue(q);
    }

    public static void printQueue(java.util.Queue q) {
        Printer.println("-- Queue --");
        q.stream().forEach(element -> {
            Printer.print(" " + element);});
    }
}
