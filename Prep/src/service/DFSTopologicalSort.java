package service;

import model.GraphNode;
import util.Printer;

import java.util.Arrays;
import java.util.Stack;

/**
 * Topological Sort of a graph.
 * Uses DFS along with a Stack.
 *
 * Ref. https://www.geeksforgeeks.org/topological-sorting/
 */
public class DFSTopologicalSort {
    private static Stack<GraphNode<Integer>> reverseTopologicalSort;

    public static void prepareDFS() {
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

        // Graph<Integer> g = new Graph<Integer>(Arrays.asList(e, d, c, b, a, o));
        Graph<Integer> g = new Graph<Integer>(Arrays.asList(o, a, b, c, d, e));

        // initialize stack
        reverseTopologicalSort = new Stack<>();

        // iterate over all the nodes (vertices) of the graph as long as they are not already visited.
        // this enables us to reach all the vertices even if the graph is disconnected.
        g.getNodes().stream().forEach(node -> {
          if (!node.getIsVisited()) {
            DFS(node);
          }
        });

        // Print the Topological sort order
        while (!reverseTopologicalSort.isEmpty()) {
            Printer.print(reverseTopologicalSort.pop().data() + " ");
        }
    }

    private static void DFS(GraphNode<Integer> node) {
        // this is not actually needed, but adding
        // just in case any node has null as its adjacent vertex.
        if (node == null) {
            return;
        }

        node.setIsVisited(true);

        // don't print yet

        for(GraphNode<Integer> adjNode: node.getAdjacentNodes()) {
            if (!adjNode.getIsVisited()) {
                DFS(adjNode);
            }
        }

        // *push* or *add* can be used interchangeably
        reverseTopologicalSort.push(node);
    }
}
