package service;

import model.TreeNode;
import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class TreeOperations {

    public static void prepExistPathWithSum() {
        // create Tree
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);

        c.setRoot(true);

        c.setLeft(b).setLeft(a);
        c.setRight(d).setRight(e);

        Tree t = new Tree(Arrays.asList(a, b, c, d, e));

        // create inorder array
        existsPathWithSum(t.getRoot(), 5);
    }

    public static void existsPathWithSum(TreeNode root, int sum) {
        if (_existsPathWithSum(root, sum)) {
            Printer.println("Found path with sum");
        } else {
            Printer.println("No path with sum");
        }
    }

    // pathWithSum(node, sum) = pathWithSum(node.left, sum - node.data) || pathWithSum(node.right, sum - node.data)
    private static boolean _existsPathWithSum(TreeNode node, int sum) {
        if (sum == 0) {
            return true;
        }

        if (node == null) {
            return false;
        }

        return (_existsPathWithSum(node.getLeft(), sum - node.getData())
                || _existsPathWithSum(node.getRight(), sum - node.getData()));
    }
}
