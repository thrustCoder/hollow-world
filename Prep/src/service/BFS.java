package service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import model.GraphNode;
import model.VisitedState;
import util.Printer;

/**
 * Created by rpsin on 10/16/2016.
 */
public class BFS {
    public static void prepareBFS() {
        // Q Gayle Lackmann
        // page 221

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

        if (BFS(a, e)) {
            Printer.println("There is a path!");
        } else {
            Printer.println("Sorry no path");
        }
    }

    public static boolean BFS(GraphNode<Integer> start, GraphNode<Integer> end) {

        // initialize queue
        // Queue is an interface, so we use LinkedList to implement Queue
        Queue<GraphNode<Integer>> q = new LinkedList<>();

        // start state
        start.setState(VisitedState.VISITING);
        q.add(start);

        // until the queue is empty
        while (!q.isEmpty()) {
            GraphNode<Integer> node = q.poll();

            // for each node in adjacent nodes
            for (GraphNode<Integer> adjNode: node.getAdjacentNodes()) {

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
