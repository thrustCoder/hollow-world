package service;

import model.LinkedListNode;
import model.PartialLLSum;
import util.Printer;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by rpsin on 10/8/2016.
 */
public class LinkedList {

    // Add two LL
    // page 191 in Lackmann "follow up" part
    public static void prepAdditionOfLLProper() {
        LinkedListNode l11 = new LinkedListNode(1);
        LinkedListNode l12 = new LinkedListNode(2);
        LinkedListNode l13 = new LinkedListNode(3);
        LinkedListNode l14 = new LinkedListNode(4);

        LinkedListNode l21 = new LinkedListNode(7);
        LinkedListNode l22 = new LinkedListNode(6);
        LinkedListNode l23 = new LinkedListNode(7);

        l11.setNext(l12).setNext(l13).setNext(l14);
        l21.setNext(l22).setNext(l23);

        Printer.println("List 1");
        print(l12);

        Printer.println("List 2");
        print(l21);

        LinkedListNode l31 = addListsProper(l11, l21);
        Printer.println("Added list");
        print(l31);
    }

    public static LinkedListNode addListsProper(LinkedListNode l11, LinkedListNode l21) {
        if (length(l11) < length(l21)) {
            l11 = padFront(l11, length(l21));
        } else if (length(l21) < length(l11)) {
            l21 = padFront(l21, length(l11));
        }

        Printer.println("Padded List 1");
        print(l11);

        Printer.println("Padded List 2");
        print(l21);

        PartialLLSum l31 = addListsProperR(l11, l21);
        if (l31.getCarry() == 1) {
            LinkedListNode l41 = new LinkedListNode(1);
            l41.setNext(l31.getPartialSumNode());
            return l41;
        }

        return l31.getPartialSumNode();
    }

    private static PartialLLSum addListsProperR(LinkedListNode l11, LinkedListNode l21) {
        if (l11 == null && l21 == null) {
            PartialLLSum pSum3 = new PartialLLSum();
            pSum3.setCarry(0);
            return pSum3;
        }

        PartialLLSum partialSum = addListsProperR((l11 != null ? l11.next() : null),
                (l21 != null ? l21.next() : null));

        int value = l11.data() + l21.data() + partialSum.getCarry();

        LinkedListNode node = new LinkedListNode(value % 10);
        node.setNext(partialSum.getPartialSumNode());

        PartialLLSum pSum2 = new PartialLLSum();
        pSum2.setPartialSumNode(node);
        pSum2.setCarry(value/10);

        return pSum2;
    }

    private static LinkedListNode padFront(LinkedListNode head, int length) {
        LinkedListNode newHead = new LinkedListNode(0);
        LinkedListNode newCurrent = newHead;

        int numNewNodes = length - length(head);
        for (int i = 1; i < numNewNodes; i++) {
            newCurrent.setNext(new LinkedListNode(0));
            newCurrent = newCurrent.next();
        }

        newCurrent.setNext(head);
        return newHead;
    }

    private static int length(LinkedListNode head) {
        int length = 0;
        LinkedListNode current = head;

        while (current != null) {
            length += 1;
            current = current.next();
        }

        return length;
    }

    // Add two LL in reverse
    // page 190 in Lackmann book
    public static void prepAdditionOfLL() {
        LinkedListNode l11 = new LinkedListNode(7);
        LinkedListNode l12 = new LinkedListNode(1);
        LinkedListNode l13 = new LinkedListNode(7);

        LinkedListNode l21 = new LinkedListNode(5);
        LinkedListNode l22 = new LinkedListNode(9);
        LinkedListNode l23 = new LinkedListNode(2);

        l11.setNext(l12).setNext(l13);
        l21.setNext(l22).setNext(l23);

        Printer.println("List 1");
        print(l11);

        Printer.println("List 2");
        print(l21);

        LinkedListNode l31 = addListsRecursive(l11, l21, 0);
        Printer.println("Added list");
        print(l31);
    }

    public static LinkedListNode addListsRecursive(LinkedListNode l11, LinkedListNode l21, int carry) {
        if (l11 == null && l21 == null && carry == 0) {
            return null;
        }

        int value = 0;
        if (l11 != null) {
            value += l11.data();
        }
        if (l21 != null) {
            value += l21.data();
        }
        value += carry;

        LinkedListNode node = new LinkedListNode(value % 10);
        carry = value/10;

        if (l11 != null && l21 != null) {
            node.setNext(addListsRecursive(l11.next(), l21.next(), carry));
        } else if (l11 == null && l21 == null) {
            node.setNext(addListsRecursive(null, null, carry));
        } else if (l11 == null) {
            node.setNext(addListsRecursive(null, l21.next(), carry));
        } else {
            node.setNext(addListsRecursive(l11.next(), null, carry));
        }

        return node;
    }

    public static void copyListWithRandomPointer() {
        // Q MSFT question to copy a linked list which can contain circles, and each node has an additional random pointer to some other node
        // https://www.careercup.com/question?id=15294742

        // create list
        LinkedListNode l11 = new LinkedListNode(1);
        LinkedListNode l12 = new LinkedListNode(2);
        LinkedListNode l13 = new LinkedListNode(3);
        LinkedListNode l14 = new LinkedListNode(4);
        l11.setNext(l12);
        l12.setNext(l13);
        l13.setNext(l14);
        l14.setNext(l12);
        l11.setOther(l14);
        System.out.println("--List 1 nexts--");
        print(l11);
        System.out.println("--List 1 others--");
        printWithOther(l11);

        // copy list
        LinkedListNode l21 = copyListWithRandomPointer(l11);
        System.out.println("--Copied List nexts--");
        print(l21);
        System.out.println("--Copied List otherss--");
        printWithOther(l21);
    }

    public static LinkedListNode copyListWithRandomPointer(LinkedListNode head) {
        LinkedListNode current = head;
        LinkedListNode head2 = new LinkedListNode(0);
        LinkedListNode current2 = new LinkedListNode(0);

        ConcurrentMap<LinkedListNode, LinkedListNode> nodeMap = new ConcurrentHashMap<>();
        Set<LinkedListNode> visitedSet = new HashSet<>();

        while (current != null && !visitedSet.contains(current)) {
            LinkedListNode newNode = new LinkedListNode(current.data());

            if (current == head) {
                head2 = newNode;
                current2 = head2;
            } else {
                current2.setNext(newNode);
                current2 = current2.next();
            }

            nodeMap.put(current, current2);

            visitedSet.add(current);
            current = current.next();
        }

        current = head;
        visitedSet = new HashSet<>();
        current2 = head2;
        LinkedListNode currentOther = current.other();

        while (current != null
            && !visitedSet.contains(current)
            && currentOther != null
            && nodeMap.containsKey(currentOther)) {

            current2.setOther(nodeMap.get(currentOther));

            visitedSet.add(current);
            current = current.next();
            currentOther = current.other();
            current2 = current2.next();
        }

        return head2;
    }

    public static void intersectSortedLinkedLists() {
        // Q Find intersection of two sorted lists
        // https://www.careercup.com/page?pid=linkedin-interview-questions

        // create lists
        LinkedListNode l11 = new LinkedListNode(1);
        LinkedListNode l12 = new LinkedListNode(3);
        LinkedListNode l13 = new LinkedListNode(5);
        l11.setNext(l12);
        l12.setNext(l13);
        System.out.println("--List 1--");
        print(l11);

        LinkedListNode l21 = new LinkedListNode(2);
        LinkedListNode l22 = new LinkedListNode(4);
        LinkedListNode l23 = new LinkedListNode(6);
        LinkedListNode l24 = new LinkedListNode(8);
        l21.setNext(l22);
        l22.setNext(l23);
        l23.setNext(l24);
        System.out.println("--List 2--");
        print(l21);

        // merge lists
        LinkedListNode l31 = intersectSortedLinkedLists(l11, l21);
        System.out.println("--Sorted Intersection of List--");
        print(l31);
    }

    private static LinkedListNode intersectSortedLinkedLists(LinkedListNode l11, LinkedListNode l21) {
        LinkedListNode l31 = l11;

        LinkedListNode currentL3 = l31;
        LinkedListNode prevL3 = l31;
        LinkedListNode currentL2 = l21;

        while (currentL3 != null && currentL2 != null) {
            if (currentL3.data() == currentL2.data()) {
                // just advance pointers
                prevL3 = currentL3;
                currentL3 = currentL3.next();
                currentL2 = currentL2.next();
            } else if (currentL3.data() < currentL2.data()) {
                // delete node currentL3
                if (prevL3 == currentL3) {
                    // if first node to be deleted
                    l31 = currentL3.next();
                    currentL3 = l31;
                    prevL3 = l31;
                } else {
                    prevL3.setNext(currentL3.next());
                    currentL3 = prevL3.next();
                }

            } else {
                // just advance currentL2
                currentL2 = currentL2.next();
            }
        }

        if (currentL2 == null && currentL3 != null) {
            prevL3.setNext(null);
        }

        return l31;
    }

    private static void print(LinkedListNode head) {
        LinkedListNode current = head;
        boolean isFirst = true;

        Set<LinkedListNode> visitedSet = new HashSet<>();

        while (current != null && !visitedSet.contains(current)) {
            if (isFirst) {
                System.out.print(current.data());
                isFirst = false;
            } else {
                System.out.print(" -> " + current.data());
            }

            visitedSet.add(current);
            current = current.next();
        }
        System.out.print("\n\n");
    }

    private static void printWithOther(LinkedListNode head) {
        LinkedListNode current = head;
        boolean isFirst = true;

        Set<LinkedListNode> visitedSet = new HashSet<>();

        while (current != null && !visitedSet.contains(current)) {
            if (isFirst) {
                System.out.print(current.data());
                isFirst = false;
            } else {
                System.out.print(" -> " + current.data());
            }

            visitedSet.add(current);
            current = current.other();
        }
        System.out.print("\n\n");
    }

    /**
     * Uses stacks to reverse a linked list in place.
     */
    private static LinkedListNode reverseUsingStack(LinkedListNode head) {
        Stack<LinkedListNode> stack = new Stack<>();
        LinkedListNode current = head;

        while (current != null) {
            stack.push(current);
            current = current.next();
        }

        LinkedListNode newHead = stack.pop();
        current = newHead;

        while (!stack.isEmpty()) {
            LinkedListNode topNode = stack.pop();
            current.setNext(topNode);
            current = current.next();
        }
        // set the next of last node to null
        current.setNext(null);

        return newHead;
    }

    /**
     * Uses three pointers - prevNode, currentNode and nextNode to reverse a linked list in place.
     */
    private static LinkedListNode reverseIterativeInPlace(final LinkedListNode head) {
        LinkedListNode currentNode = head;
        LinkedListNode prevNode = null;

        while (currentNode != null) {
            LinkedListNode nextNode = currentNode.next();

            // similar to swapping
            currentNode.setNext(prevNode);
            prevNode = currentNode;
            currentNode = nextNode;
        }
        LinkedListNode newHead = prevNode;
        return newHead;
    }

    public static void reverseLinkedList() {
        // create list
        LinkedListNode l11 = new LinkedListNode(1);
        LinkedListNode l12 = new LinkedListNode(3);
        LinkedListNode l13 = new LinkedListNode(5);
        LinkedListNode l14 = new LinkedListNode(7);
        l11.setNext(l12);
        l12.setNext(l13);
        l13.setNext(l14);
        System.out.println("--List--");
        LinkedList.print(l11);

        // reverse list
        LinkedListNode l21 = LinkedList.reverseUsingStack(l11);
        System.out.println("--Reversed List--");
        LinkedList.print(l21);
    }

    public static void mergeSortedLinkedLists() {
        // Oracle interview question- merge sorted linked lists

        // create lists
        LinkedListNode l11 = new LinkedListNode(1);
        LinkedListNode l12 = new LinkedListNode(3);
        LinkedListNode l13 = new LinkedListNode(5);
        l11.setNext(l12);
        l12.setNext(l13);
        System.out.println("--List 1--");
        LinkedList.print(l11);

        LinkedListNode l21 = new LinkedListNode(2);
        LinkedListNode l22 = new LinkedListNode(4);
        LinkedListNode l23 = new LinkedListNode(6);
        LinkedListNode l24 = new LinkedListNode(7);
        l21.setNext(l22);
        l22.setNext(l23);
        l23.setNext(l24);
        System.out.println("--List 2--");
        LinkedList.print(l21);

        // merge lists
        LinkedListNode l31 = mergeSortedLinkedLists(l11, l21);
        System.out.println("--Merge List--");
        LinkedList.print(l31);
    }

    private static LinkedListNode mergeSortedLinkedLists(LinkedListNode l1, LinkedListNode l2) {
        // 1->3->5 2->4->6->8

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        LinkedListNode l3 = l1;
        LinkedListNode currentL2 = l2;
        LinkedListNode currentL3 = l3;
        LinkedListNode prevL3 = currentL3;

        while (currentL2 != null && currentL3 != null) {
            if (currentL3.data() >= currentL2.data()) {
                // insert node
                LinkedListNode newNode = new LinkedListNode(currentL2.data());
                newNode.setNext(prevL3.next());
                prevL3.setNext(newNode);

                currentL2 = currentL2.next();
            }

            prevL3 = currentL3;
            currentL3 = currentL3.next();
        }

        if (currentL3 == null) {
            prevL3.setNext(currentL2);
        }

        return l3;
    }
}
