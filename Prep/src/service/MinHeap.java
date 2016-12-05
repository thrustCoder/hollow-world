package service;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rpsin on 11/27/2016.
 */
public class MinHeap<T> extends PriorityQueue<T> {
    public MinHeap(int capacity) {
        super(capacity, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return Integer.parseInt(o1.toString()) - Integer.parseInt(o2.toString());
            }
        });
    }

    // Iterator of priority queue is not guaranteed to traverse in any particular order
    // So the print method is useless
    public void printMinHeap() {}
}
