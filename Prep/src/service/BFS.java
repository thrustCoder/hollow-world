package service;

import model.GraphNode;
import model.VisitedState;
import util.Printer;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by rpsin on 10/16/2016.
 */
public class BFS {
    public static void prepareBFS() {
        // Q Gayle Lackmann
        // page 221

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
        if (BFS(g, a, e)) {
            Printer.println("There is a path!");
        } else {
            Printer.println("Sorry no path");
        }
    }

    public static boolean BFS(Graph g, GraphNode start, GraphNode end) {
        // unset visited state
        g.getNodes().parallelStream()
                .forEach(node -> {node.setState(VisitedState.UNVISITED);});

        // initialize queue
        // Queue is an abstract class, so we use LinkedList to mimic Queue
        java.util.LinkedList<GraphNode> q = new LinkedList<>();

        // start state
        start.setState(VisitedState.VISITING);
        q.add(start);

        // until the queue is empty
        while (!q.isEmpty()) {
            GraphNode node = q.removeFirst();
            Printer.print(node.data() + " ");

            // for each node in adjacent nodes
            for (GraphNode adjNode: node.getAdjacentNodes()) {

                // you want to visit only if unvisited
                if (adjNode.getState() == VisitedState.UNVISITED) {
                    adjNode.setState(VisitedState.VISITING);

                    // processing
                    // this block (and an end node) is only required for the purposes of this problem (finding a path).
                    // not required for a normal BFS traversal, which only requires a start node.
                    if (adjNode == end) {
                        return true;
                    }

                    q.add(adjNode);
                }
            }
            node.setState(VisitedState.VISITED);
        }

        return false;
    }
}
