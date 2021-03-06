package service;

import model.GraphNode;
import model.VisitedState;
import util.Printer;

import java.util.Arrays;

/**
 * Created by rpsin on 10/16/2016.
 */
public class DFS {

    public static void prepareDFS() {
        // create a Graph
        GraphNode a = new GraphNode<Integer>(1);
        GraphNode b = new GraphNode<Integer>(2);
        GraphNode c = new GraphNode<Integer>(3);
        GraphNode d = new GraphNode<Integer>(4);
        GraphNode e = new GraphNode<Integer>(5);

        /*
            a(1) ⃗ b(2)
            ↓
            c(3) ⃗ d(4) ⃗ e(5)
         */
        a.setAdjacentNodes(Arrays.asList(b, c));
        c.setAdjacentNodes(Arrays.asList(d));
        d.setAdjacentNodes(Arrays.asList(e));

        if (DFS(b, e)) {
            Printer.println("There is a path!");
        } else {
            Printer.println("Sorry no path");
        }
    }

    private static boolean DFS(GraphNode<Integer> node, GraphNode<Integer> end) {
        // base case of recursion
        if (node == null) {
            return false;
        }

        // set VISITING state
        node.setState(VisitedState.VISITING);

        // processing
        // this block (and an end node) is only required for the purposes of this problem (finding a path).
        // not required for a normal DFS traversal, which only requires a start node.
        if (node == end) {
            return true;
        }

        boolean pathExists = false;
        for(GraphNode<Integer> adjNode: node.getAdjacentNodes()) {
            if (adjNode.getState() == VisitedState.UNVISITED) {
                pathExists = DFS(adjNode, end);
                if (pathExists) {
                    return true;
                }
            }
        }

        node.setState(VisitedState.VISITED);
        return pathExists;
    }
}
