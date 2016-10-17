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

    public static void prepFindPathsWithSum() {
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
        findPathsWithSum(t.getRoot(), 7);
    }

    public static void findPathsWithSum(TreeNode root, int sum) {
        List<TreeNode> path = new ArrayList<>();
        findPathsWithSum(root, sum, 0, path);
    }

    // TODO: Fix this recursion logic, currently broken
    public static void findPathsWithSum(TreeNode node, int sum, int pathSum, List<TreeNode> path) {
        if (node == null) {
            return;
        }

        findPathsWithSum(node.getLeft(), sum, pathSum, path);

        pathSum += node.getData();
        path.add(node);

        findPathsWithSum(node.getRight(), sum, pathSum, path);

        if (pathSum == sum) {
            // print path
            Printer.println("\n Found path");
            path.stream()
                    .forEach(n -> {
                        Printer.print(n.getData() + " ");
                    });
        }
    }
}
