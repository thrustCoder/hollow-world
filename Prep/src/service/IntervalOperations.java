package service;

import model.IntervalPair;
import util.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpsin on 10/15/2016.
 */
public class IntervalOperations {

    public static void mergeIntervals () {
        // create intervals
        IntervalPair p1 = new IntervalPair(-10, -1);
        IntervalPair p2 = new IntervalPair(0, 2);
        IntervalPair p3 = new IntervalPair(4, 10);
        IntervalPair p4 = new IntervalPair(-5, 1);

        ArrayList<IntervalPair> list = new ArrayList<>();
        list.add(p2);
        list.add(p1);
        list.add(p3);

        Printer.println("Original list");
        printList(list);

        list.sort((o1, o2) -> o1.left() - o2.left());
        Printer.println("Sorted list");
        printList(list);

        mergeIntervals(list, p4);
    }

    private static void mergeIntervals(List<IntervalPair> list, IntervalPair p) {
        list.add(p);
        list.sort((o1, o2) -> o1.left() - o2.left());

        Printer.println("Added p");
        printList(list);

        pruneIntervals(list);
        Printer.println("Pruned p");
        printList(list);

        list = mergeList(list);
        Printer.println("Final list");
        printList(list);
    }

    private static List<IntervalPair> mergeList(final List<IntervalPair> list) {
        for (IntervalPair pair: list) {
            int index = list.indexOf(pair);
            if (index > 0
                    && pair.left() == (list.get(index - 1).right() + 1)) {
                IntervalPair prev = list.get(index - 1);
                pair.setLeft(prev.left());
                pair.setRight(Math.max(pair.right(), prev.right()));
            }
        }

        final List<IntervalPair> finalList = new ArrayList<>();
        list.stream()
                .forEach(pair -> {
                    int index = list.indexOf(pair);
                    if (index < (list.size() - 1)) {
                        if (pair.left() != list.get(index + 1).left()) {
                            finalList.add(pair);
                        }
                    } else {
                        finalList.add(pair);
                    }
                });

        return finalList;
    }

    private static void pruneIntervals(List<IntervalPair> list) {
        // TODO: remove contained intervals first

        list.stream()
                .forEach(pair -> {
                    int index = list.indexOf(pair);
                    if (index > 0 && pair.left() < list.get(index - 1).right()) {
                        int prevRight = list.get(index - 1).right();
                        pair.setLeft(prevRight + 1);
                    }
                });
    }

    private static void printList(List<IntervalPair> list) {
        list.stream()
                .forEach(pair -> {
                    pair.printPair();
                    if (list.indexOf(pair) < list.size() - 1) {
                        System.out.print(" -> ");
                    }
                });

        Printer.println("");
        Printer.println("");
    }
}
