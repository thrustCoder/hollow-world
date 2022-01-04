package service;

import model.GraphNode;

import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class Graph<T> {
    private List<GraphNode<T>> nodes;

    public Graph(List<GraphNode<T>> n) {
        nodes = n;
    }

    public List<GraphNode<T>> getNodes() {
        return nodes;
    }
    public void setNodes(List<GraphNode<T>> n) {
        nodes = n;
    }
}
