package service;

import model.TreeNode;
import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);

        /*
                            c(3)
                          /     \
                      b(2)      d(4)
                        /       /   \
                      a(1)   f(6)   e(5)
                                      \
                                     g(7)
        */
        c.setRoot(true);

        c.setLeft(b).setLeft(a);

        TreeNode dNode = c.setRight(d);
        dNode.setLeft(f);

        TreeNode eNode = dNode.setRight(e);
        eNode.setRight(g);

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

    public static void printLevelsOfTree() {
        final Tree t = prepCreateArray();

        levelsOfTree(t.getRoot());
    }

    /**
     * Given a Binary tree, print out all it's root to leaf paths.
     */
    public static void printRootToLeafPaths() {
        final Tree t = prepCreateArray();

        printRootToLeafPathsRecursive(t.getRoot(), new ArrayList<>(), 0);
    }

    /**
     * Given a Binary tree, traverse a Binary tree in a zigzag order.
     */
    public static void printZigzagTraversal() {
        final Tree t = prepCreateArray();

        List<List<Integer>> result = new ArrayList<>();

        zigzagTraverse(t.getRoot(), result);

        // prints the zigzag traversal
        // result.stream().forEach(list -> Printer.println(list));
        // same as above
        result.stream().forEach(Printer::println);
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
    private static void levelOrderTraverse(final TreeNode node, final List<Integer> array) {

        // validate node
        if (node == null) {
            return;
        }

        // level order bfs queue
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

    /**
     * To print levels of a tree,
     * we use Level order traversal with a slight modification to identify the node which is
     * the last in the current level.
     *
     * This method also prints the following:
     * 1. the count of levels
     * 2. the max width of tree (i.e. the max no. of nodes at any level)
     * 3. the max sum of any level
     */
    private static void levelsOfTree(final TreeNode node) {

        // validate node
        if (node == null) {
            return;
        }

        // level order bfs queue
        final Queue<TreeNode> q = new LinkedList<>();

        // insert root
        q.add(node);

        int countLevels = 0;
        int maxWidth = 0;
        int maxSumOfLevel = 0;

        while (!q.isEmpty()) {

            final int currLevelSize = q.size();
            maxWidth = Math.max(maxWidth, currLevelSize);
            int sumOfLevel = 0;

            for (int i = 0; i < currLevelSize; i ++) {

                final TreeNode poppedNode = q.poll();
                Printer.print(poppedNode.getData() + " ");
                sumOfLevel += poppedNode.getData();

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

            // level done
            Printer.println();
            countLevels ++;
            maxSumOfLevel = Math.max(maxSumOfLevel, sumOfLevel);
            sumOfLevel = 0;
        }

        Printer.println("No. of levels = " + countLevels);
        Printer.println("Max width = " + maxWidth);
        Printer.println("Max sum of level = " + maxSumOfLevel);
    }

    /**
     * Does a preorder traversal and adds to path if the current node is a leaf.
     *
     * @param node
     * @param pathList   List containing path information.
     * @param pathIndex  Keeps track of path length at each recursion.
     */
    private static void printRootToLeafPathsRecursive(
            final TreeNode node,
            final List<TreeNode> pathList,
            int pathIndex) {

        if (node != null) {
            pathList.add(pathIndex, node);
            pathIndex ++;

            if (node.getLeft() == null && node.getRight() == null) {
                // reached a leaf, so print out pathList
                // note that we don't print out the entire array list
                // but only from 0 to pathIndex.
                for (int i = 0; i < pathIndex; i ++) {
                    Printer.print(pathList.get(i).getData() + " ");
                }
                Printer.println();
            } else {
                printRootToLeafPathsRecursive(node.getLeft(), pathList, pathIndex);
                printRootToLeafPathsRecursive(node.getRight(), pathList, pathIndex);
            }
        }
    }

    /**
     * We utilize level order traversal here. We include a stack to push elements whenever the level
     * has to be printed in reverse order.
     * @param node
     * @param result
     */
    private static void zigzagTraverse(final TreeNode node, final List<List<Integer>> result) {

        // validate node
        if (node == null) {
            return;
        }

        // level order bfs queue
        final Queue<TreeNode> q = new LinkedList<>();

        // insert root
        q.add(node);

        boolean leftToRight = true;

        while (!q.isEmpty()) {

            int currLevelSize = q.size();
            final List<Integer> levelData = new ArrayList<>();
            final Stack<Integer> reverseLevelDataStack = new Stack<>();

            for (int i = 0; i < currLevelSize; i ++) {
                final TreeNode poppedNode = q.poll();
                if (leftToRight) {
                    levelData.add(poppedNode.getData());
                } else {
                    reverseLevelDataStack.push(poppedNode.getData());
                }

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

            // level done
            if (levelData.isEmpty()) {
                while (!reverseLevelDataStack.isEmpty()) {
                    levelData.add(reverseLevelDataStack.pop());
                }
            }
            result.add(levelData);
            leftToRight = !leftToRight;
        }
    }
}
