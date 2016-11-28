package service;

import util.Printer;

/**
 * Created by rpsin on 11/27/2016.
 */
public class HeapOperations {
    public static void prepTestMinHeap() {
        MinHeap<Integer> minHeap = new MinHeap<Integer>(15);

        minHeap.offer(2);
        minHeap.offer(5);
        minHeap.offer(3);
        minHeap.offer(7);

        Printer.println("Peek of max heap: " + minHeap.peek());
    }

    public static void prepTestMaxHeap() {
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(15);

        maxHeap.offer(2);
        maxHeap.offer(5);
        maxHeap.offer(3);
        maxHeap.offer(7);

        Printer.println("Peek of max heap: " + maxHeap.peek());
    }
}
