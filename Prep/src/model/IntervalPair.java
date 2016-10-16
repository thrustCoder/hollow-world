package model;

/**
 * Created by rpsin on 10/15/2016.
 */
public class IntervalPair {
    private int left;
    private int right;

    public int left() {
        return left;
    }
    public int right() {
        return right;
    }

    public void setLeft(int l) {
        left = l;
    }
    public void setRight(int r) {
        right = r;
    }

    public IntervalPair(int l, int r) {
        left = l;
        right = r;
    }

    public void printPair() {
        System.out.print("[" + left + ", " + right + "]");
    }
}
