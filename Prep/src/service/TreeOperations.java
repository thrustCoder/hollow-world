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

        c.setLeft(b);
        c.setRight(d);

        c.getLeft().setLeft(a);
        c.getRight().setRight(e);

        Tree t = new Tree(Arrays.asList(a, b, c, d, e));

        // create inorder array
        existsPathWithSum(t.getRoot(), 7);
    }

    public static void existsPathWithSum(TreeNode root, int sum) {
        if (_existsPathWithSum(root, sum)) {
            Printer.println("Found path with sum");
        } else {
            Printer.println("No path with sum");
        }
    }

    private static boolean _existsPathWithSum(TreeNode node, int sum) {
        if (node == null) {
            return sum == 0;
        }

        sum -= node.getData();
        if (sum == 0) {
            return true;
        }

        boolean ans = false;

        ans = _existsPathWithSum(node.getLeft(), sum);
        if (!ans) {
            ans = _existsPathWithSum(node.getRight(), sum);
        }

        return ans;
    }
}
