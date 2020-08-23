package service;

import model.TreeNode;
import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by rpsin on 10/16/2016.
 */
public class TreeTraversal {
    private static Tree prepCreateArray() {
        // create Tree
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);

        /*
                            c(3)
                          /     \
                      b(2)      d(4)
                        /           \
                      a(1)          e(5)
        */
        c.setRoot(true);

        c.setLeft(b).setLeft(a);
        c.setRight(d).setRight(e);

        return new Tree(Arrays.asList(a, b, c, d, e));
    }

    public static void printInorderTraversal() {
        final Tree t = prepCreateArray();

        List<Integer> array = new ArrayList<>();

        inorderTraverse(t.getRoot(), array);

        // print that array
        // prints 1 2 3 4 5
        array.stream().forEach(data -> Printer.print(data + " "));
    }

    public static void printLevelOrderTraversal() {
        final Tree t = prepCreateArray();

        List<Integer> array = new ArrayList<>();

        levelOrderTraverse(t.getRoot(), array);

        // print that array
        // prints 3 2 4 1 5
        array.stream().forEach(data -> Printer.print(data + " "));
    }

    /**
     * Fun Fact: Inorder traversal of a BST is sorted.
     * @param node
     * @param array
     */
    private static void inorderTraverse(TreeNode node, List<Integer> array) {
        // base case
        if (node == null) {
            return;
        }

        inorderTraverse(node.getLeft(), array);
        array.add(node.getData());
        inorderTraverse(node.getRight(), array);
    }

    /**
     * Level order of a tree is essentially a BFS traversal of tree.
     * We don't need to worry about VISITED states because a tree is an acyclic graph.
     *
     * @param node  root of tree.
     * @param array array in level order.
     */
    private static void levelOrderTraverse(TreeNode node, List<Integer> array) {
        final Queue<TreeNode> q = new LinkedList<>();

        // insert root
        q.add(node);

        while (!q.isEmpty()) {
            final TreeNode poppedNode = q.poll();
            array.add(poppedNode.getData());

            // insert left child if it is non null
            TreeNode leftChild = poppedNode.getLeft();
            if (leftChild != null) {
                q.add(leftChild);
            }

            // insert right child if it is non null
            TreeNode rightChild = poppedNode.getRight();
            if (rightChild != null) {
                q.add(rightChild);
            }
        }
    }
}
