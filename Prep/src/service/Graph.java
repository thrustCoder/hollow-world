package service;

import model.GraphNode;

import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class Graph {
    private List<GraphNode> nodes;

    public Graph(List<GraphNode> n) {
        nodes = n;
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }
    public void setNodes(List<GraphNode> n) {
        nodes = n;
    }
}
