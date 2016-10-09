import java.util.Stack;

/**
 * Created by rpsin on 10/8/2016.
 */
public class LinkedList {

    public static void print(LinkedListNode head) {
        LinkedListNode current = head;
        boolean isFirst = true;

        while (current != null) {
            if (isFirst) {
                System.out.print(current.data());
                isFirst = false;
            } else {
                System.out.print(" -> " + current.data());
            }

            current = current.next();
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
}
