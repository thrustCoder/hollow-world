package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import model.GraphEdge;
import model.GraphNode;
import util.Printer;

/**
 * Given a graph containing nodes and their connections (edges) having weights,
 * find the shortest path from a source to other nodes.
 */
public class Dijkstras {
  public static void prepareGraph() {
    // create a Graph
    GraphNode<Integer> o = new GraphNode<>(0);
    GraphNode<Integer> a = new GraphNode<>(1);
    GraphNode<Integer> b = new GraphNode<>(2);
    GraphNode<Integer> c = new GraphNode<>(3);
    GraphNode<Integer> d = new GraphNode<>(4);
    GraphNode<Integer> e = new GraphNode<>(5);
    GraphNode<Integer> f = new GraphNode<>(6);
    GraphNode<Integer> g = new GraphNode<>(7);
    GraphNode<Integer> h = new GraphNode<>(8);

    /*
      See graph diagram in https://www.geeksforgeeks.org/dijkstras-algorithm-for-adjacency-list-representation-greedy-algo-8/
     */
    o.setAdjacentEdges(Arrays.asList(new GraphEdge<>(a, 4), new GraphEdge<>(g, 8)));
    a.setAdjacentEdges(Arrays.asList(new GraphEdge<>(b, 8), new GraphEdge<>(g, 11), new GraphEdge<>(a, 4)));
    b.setAdjacentEdges(Arrays.asList(new GraphEdge<>(a, 8), new GraphEdge<>(h, 2), new GraphEdge<>(c, 7), new GraphEdge<>(e, 4)));
    c.setAdjacentEdges(Arrays.asList(new GraphEdge<>(b, 7), new GraphEdge<>(e, 14), new GraphEdge<>(d, 9)));
    d.setAdjacentEdges(Arrays.asList(new GraphEdge<>(e, 10), new GraphEdge<>(c, 9)));
    e.setAdjacentEdges(Arrays.asList(new GraphEdge<>(f, 2), new GraphEdge<>(b, 4), new GraphEdge<>(c, 14), new GraphEdge<>(d, 10)));
    f.setAdjacentEdges(Arrays.asList(new GraphEdge<>(h, 6), new GraphEdge<>(g, 1), new GraphEdge<>(e, 2)));
    g.setAdjacentEdges(Arrays.asList(new GraphEdge<>(o, 8), new GraphEdge<>(a, 11), new GraphEdge<>(f, 1), new GraphEdge<>(h, 7)));
    h.setAdjacentEdges(Arrays.asList(new GraphEdge<>(b, 2), new GraphEdge<>(g, 7), new GraphEdge<>(f, 6)));

    Graph<Integer> graph = new Graph<>(Arrays.asList(o, a, b, c, d, e, f, g, h));

    Map<GraphNode<Integer>, Integer> resutltMinDistance = visitDijkstras(graph, o);

    /*
      Prints:

      Node -> MinDistance

      4 -> 21

      2 -> 12

      6 -> 9

      8 -> 14

      5 -> 11

      3 -> 19

      7 -> 8

      0 -> 0

      1 -> 4
    */
    Printer.println("Node -> MinDistance");
    resutltMinDistance.entrySet().stream().forEach(entry -> Printer.println(entry.getKey().data() + " -> " + entry.getValue()));
  }

  private static Map<GraphNode<Integer>, Integer> visitDijkstras(Graph<Integer> graph, GraphNode<Integer> source) {
    Map<GraphNode<Integer>, Integer> minDistanceFromSourceMap = new HashMap<>();
    graph.getNodes().stream().forEach(node -> minDistanceFromSourceMap.put(node, Integer.MAX_VALUE));
    minDistanceFromSourceMap.put(source, 0);

    PriorityQueue<GraphEdge<Integer>> distFromSourceMinHeap = new PriorityQueue<>((a, b) -> a.getEdgeWeight() - b.getEdgeWeight());
    distFromSourceMinHeap.add(new GraphEdge<>(source, 0));

    while (!distFromSourceMinHeap.isEmpty()) {
      GraphEdge<Integer> currEdge = distFromSourceMinHeap.poll();
      GraphNode<Integer> currNode = currEdge.getNode();

      for (GraphEdge<Integer> adjEdge : currNode.getAdjacentEdges()) {

        if (adjEdge.getEdgeWeight() + minDistanceFromSourceMap.get(currNode) < minDistanceFromSourceMap.get(adjEdge.getNode())) {
          minDistanceFromSourceMap.put(adjEdge.getNode(), adjEdge.getEdgeWeight() + minDistanceFromSourceMap.get(currNode));

          distFromSourceMinHeap.add(new GraphEdge<>(adjEdge.getNode(), minDistanceFromSourceMap.get(adjEdge.getNode())));
        }
      }
    }

    return minDistanceFromSourceMap;
  }
}
