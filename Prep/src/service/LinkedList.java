package service;

import model.LinkedListNode;

import java.util.*;

/**
 * Created by rpsin on 10/8/2016.
 */
public class LinkedList {

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

        Map<LinkedListNode, LinkedListNode> nodeMap = new HashMap<>();
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

        while (current != null && !visitedSet.contains(current)) {
            current2.setOther(nodeMap.get(current.other()));

            visitedSet.add(current);
            current = current.next();
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

    public static void print(LinkedListNode head) {
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

    public static void printWithOther(LinkedListNode head) {
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

    public static LinkedListNode reverse(LinkedListNode head) {
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
            topNode.setNext(null);
            current.setNext(topNode);
            current = current.next();
        }

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

        // merge lists
        LinkedListNode l21 = LinkedList.reverse(l11);
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
