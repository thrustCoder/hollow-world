package service;

import model.TreeNode;
import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class TreeTraversal {
    public static void prepCreateInorderArray() {
        // create Tree
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);

        c.setRoot(true);

        b.setLeft(a);
        c.setLeft(b);

        d.setRight(e);
        c.setRight(d);

        Tree t = new Tree(Arrays.asList(a, b, c, d, e));

        // create inorder array
        createInorderArray(t);
    }

    public static void createInorderArray(Tree t) {
        List<Integer> array = new ArrayList<>();

        inorderTraverse(t.getRoot(), array);

        // print that array
        array.stream().forEach(data -> {
            Printer.print(data.toString());
        });
    }

    private static void inorderTraverse(TreeNode node, List<Integer> array) {
        if (node == null) {
            return;
        }

        inorderTraverse(node.getLeft(), array);
        array.add(node.getData());
        inorderTraverse(node.getRight(), array);
    }
}
