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
}
