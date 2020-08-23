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
        GraphNode a = new GraphNode(1);
        GraphNode b = new GraphNode(2);
        GraphNode c = new GraphNode(3);
        GraphNode d = new GraphNode(4);
        GraphNode e = new GraphNode(5);

        /*
            a(1) ⃗ b(2)
            ↓
            c(3) ⃗ d(4) ⃗ e(5)
         */
        a.setAdjacentNodes(Arrays.asList(b, c));
        c.setAdjacentNodes(Arrays.asList(d));
        d.setAdjacentNodes(Arrays.asList(e));

        Graph g = new Graph(Arrays.asList(a, b, c, d, e));
        if (DFS(g, a, e)) {
            Printer.println("There is a path!");
        } else {
            Printer.println("Sorry no path");
        }
    }

    public static boolean DFS(Graph g, GraphNode start, GraphNode end) {
        // unset initial states
        g.getNodes().parallelStream()
                .forEach(node -> {node.setState(VisitedState.UNVISITED);});

        Printer.print(String.format("DFS traversal of Graph starting from node %d = ", start.data()));
        return DFSVisit(start, end);
    }

    private static boolean DFSVisit(GraphNode node, GraphNode end) {
        // base case of recursion
        if (node == null) {
            return false;
        }

        // set VISITING state
        node.setState(VisitedState.VISITING);
        // Prints 1 2 3 4 5
        Printer.print(node.data() + " ");

        // processing
        // this block (and an end node) is only required for the purposes of this problem (finding a path).
        // not required for a normal DFS traversal, which only requires a start node.
        if (node == end) {
            return true;
        }

        boolean pathExists = false;
        for(GraphNode adjNode: node.getAdjacentNodes()) {
            if (adjNode.getState() == VisitedState.UNVISITED) {
                pathExists = DFSVisit(adjNode, end);
            }
        }

        node.setState(VisitedState.VISITED);
        return pathExists;
    }
}
