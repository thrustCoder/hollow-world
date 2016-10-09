/**
 * Created by rpsin on 10/8/2016.
 */
public class LinkedListNode {
    private int data;
    private LinkedListNode next = null;

    public void setData(int d) {
        data = d;
    }
    public void setNext(LinkedListNode n) {
        next = n;
    }

    public int data() {
        return data;
    }
    public LinkedListNode next() {
        return next;
    }

    public LinkedListNode(int d) {
        data = d;
        next = null;
    }
}
