package service;

import model.GraphNode;
import model.VisitedState;
import util.Printer;
import java.util.HashSet;

import java.util.*;
import java.util.LinkedList; BFSHash

public class BFSHash {
    public static void prepareBFS() {

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
        if (BFS(g, a, e)) {
            Printer.println("There is a path!");
        } else {
            Printer.println("Sorry no path");
        }
    }

    public static boolean BFS(Graph g, GraphNode start, GraphNode end) {

        // initialize queue
        // Queue is an abstract class, so we use LinkedList to mimic Queue
        java.util.LinkedList<GraphNode> q = new LinkedList<>();
        HashSet <GraphNode> visited = new HashSet<>();
        // start state
//        start.setState(VisitedState.VISITING);
        q.add(start);

        // until the queue is empty
        while (!q.isEmpty()) {
            GraphNode node = q.removeFirst();
            if(!visited.contains(node)){

                visited.add(node);
                System.out.println("visitedNode: " + node);
            }

            // for each node in adjacent nodes
            for (GraphNode adjNode: node.getAdjacentNodes()) {

                if(!visited.contains(adjNode)){

                    // processing
                    if (adjNode == end) {
                        return true;
                    }

                    q.add(adjNode);
                }
            }
        }

        return false;
    }
}
