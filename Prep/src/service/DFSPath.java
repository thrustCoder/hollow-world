package service;

import model.GraphNode;
import util.Printer;

import java.util.Arrays;

/**
 * Created by rpsin on 10/16/2016.
 *
 * Given two nodes, check if a path exists using DFS.
 */
public class DFSPath {

    public static void prepareDFS() {
        // create a Graph
        GraphNode<Integer> a = new GraphNode<Integer>(1);
        GraphNode<Integer> b = new GraphNode<Integer>(2);
        GraphNode<Integer> c = new GraphNode<Integer>(3);
        GraphNode<Integer> d = new GraphNode<Integer>(4);
        GraphNode<Integer> e = new GraphNode<Integer>(5);

        /*
            a(1) -> b(2)
            ↓
            c(3) -> d(4) -> e(5)
         */
        a.setAdjacentNodes(Arrays.asList(b, c));
        c.setAdjacentNodes(Arrays.asList(d));
        d.setAdjacentNodes(Arrays.asList(e));

        if (visitDFS(a, e)) {
            Printer.println("There is a path!");
        } else {
            Printer.println("Sorry no path");
        }
    }

    private static boolean visitDFS(GraphNode<Integer> node, GraphNode<Integer> end) {
        // base case of recursion
        if (node == null) {
            return false;
        }

        node.setIsVisited(true);

        // processing
        if (node == end) {
            return true;
        }

        boolean pathExists = false;
        for(GraphNode<Integer> adjNode: node.getAdjacentNodes()) {
            if (!adjNode.getIsVisited()) {
                pathExists = visitDFS(adjNode, end);
                if (pathExists) {
                    return true;
                }
            }
        }

        return pathExists;
    }
}
