package model;

/**
 * Created by rpsin on 10/8/2016.
 */
public class LinkedListNode {
    private int data;
    private LinkedListNode next = null;

    private LinkedListNode other = null; // not used conventionally

    public void setData(int d) {
        data = d;
    }
    public void setNext(LinkedListNode n) {
        next = n;
    }
    public void setOther(LinkedListNode o) {
        other = o;
    }

    public int data() {
        return data;
    }
    public LinkedListNode next() {
        return next;
    }
    public LinkedListNode other() {
        return other;
    }

    public LinkedListNode(int d) {
        data = d;
        next = null;
    }

}
