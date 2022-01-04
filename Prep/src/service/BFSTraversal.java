package service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import model.GraphNode;
import util.Printer;

/**
 * BFS Traversal of a graph.
 * Note, this will traverse ALL the nodes of the graph
 * even if they are disconnected.
 */
public class BFSTraversal {
    public static void prepareBFS() {
        // create a Graph
        GraphNode<Integer> o = new GraphNode<Integer>(0);
        GraphNode<Integer> a = new GraphNode<Integer>(1);
        GraphNode<Integer> b = new GraphNode<Integer>(2);
        GraphNode<Integer> c = new GraphNode<Integer>(3);
        GraphNode<Integer> d = new GraphNode<Integer>(4);
        GraphNode<Integer> e = new GraphNode<Integer>(5);

        /*
            e(5)     d(4)
            ↙  ↘    ↙  ↘
          b(2)  o(0)   a(1)
              ↘      ↗
                c(3)
         */
        e.setAdjacentNodes(Arrays.asList(b, o));
        d.setAdjacentNodes(Arrays.asList(a, o));
        b.setAdjacentNodes(Arrays.asList(c));
        c.setAdjacentNodes(Arrays.asList(a));

        Graph<Integer> g = new Graph<Integer>(Arrays.asList(e, d, c, b, a, o));

        // iterate over all the nodes (vertices) of the graph as long as they are not already visited.
        // this enables us to reach all the vertices even if the graph is disconnected.
        g.getNodes().stream().forEach(node -> {
            if (!node.getIsVisited()) {
                BFS(node);
            }
        });
    }

    public static void BFS(GraphNode<Integer> start) {
        Queue<GraphNode<Integer>> q = new LinkedList<>();

        q.add(start);

        while (!q.isEmpty()) {
            GraphNode<Integer> node = q.poll();

            node.setIsVisited(true);

            // Prints 5 2 0 3 1 4
            Printer.print(node.data() + " ");

            for (GraphNode<Integer> adjNode: node.getAdjacentNodes()) {

                // visit only if unvisited
                if (!adjNode.getIsVisited()) {
                    q.add(adjNode);
                }
            }
        }
    }
}
