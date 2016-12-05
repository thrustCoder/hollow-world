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

    public TreeNode setLeft(TreeNode left) {
        this.left = left;
        return this.left;
    }

    public TreeNode setRight(TreeNode right) {
        this.right = right;
        return this.right;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
