package model;

/**
 * Created by rpsin on 10/16/2016.
 */
public class TreeNode {
    private int data;
    private TreeNode left;
    private TreeNode right;
    private boolean isRoot;

    public TreeNode(int d) {
        data = d;
        left = null;
        right = null;
        isRoot = false;
    }

    public int getData() {
        return data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
