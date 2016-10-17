package model;

import sun.security.provider.certpath.AdjacencyList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class GraphNode {
    private int data;
    private VisitedState state;
    private List<GraphNode> adjacentNodes;

    public GraphNode(int d) {
        data = d;
        state = VisitedState.UNVISITED;
        adjacentNodes = new ArrayList<>();
    }

    public int data() {
        return data;
    }
    public void setData(int d) {
        data = d;
    }

    public VisitedState getState() {
        return state;
    }
    public void setState(VisitedState s) {
        state = s;
    }

    public List<GraphNode> getAdjacentNodes() {
        return adjacentNodes;
    }
    public void setAdjacentNodes(List<GraphNode> a) {
        adjacentNodes = a;
    }
}
