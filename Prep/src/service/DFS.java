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

        return DFSVisit(start, end);
    }

    private static boolean DFSVisit(GraphNode node, GraphNode end) {
        // base case of recursion
        if (node == null) {
            return false;
        }

        // set VISITING state
        node.setState(VisitedState.VISITING);

        // processing
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
