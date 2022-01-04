package service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import model.GraphNode;
import util.Printer;

/**
 * Created by rpsin on 10/16/2016.
 *
 * Given two nodes, check if a path exists using BFS.
 */
public class BFSPath {
    public static void prepareBFS() {
        // Q Gayle Lackmann
        // page 221

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

        if (visitBFS(a, e)) {
            Printer.println("There is a path!");
        } else {
            Printer.println("Sorry no path");
        }
    }

    public static boolean visitBFS(GraphNode<Integer> start, GraphNode<Integer> end) {

        // initialize queue
        // Queue is an interface, so we use LinkedList to implement Queue
        Queue<GraphNode<Integer>> q = new LinkedList<>();

        q.add(start);

        // until the queue is empty
        while (!q.isEmpty()) {
            GraphNode<Integer> node = q.poll();
            node.setIsVisited(true);

            // for each node in adjacent nodes
            for (GraphNode<Integer> adjNode: node.getAdjacentNodes()) {

                // you want to visit only if unvisited
                if (!adjNode.getIsVisited()) {
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
