package model;

/**
 * Created by rpsin on 11/16/2016.
 */
public class PartialLLSum {
    private LinkedListNode partialSumNode;
    private int carry;

    public LinkedListNode getPartialSumNode() {
        return partialSumNode;
    }

    public void setPartialSumNode(LinkedListNode partialSumNode) {
        this.partialSumNode = partialSumNode;
    }

    public int getCarry() {
        return carry;
    }

    public void setCarry(int carry) {
        this.carry = carry;
    }
}
