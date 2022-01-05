package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class GraphNode<T> {
    private T data;
    private VisitedState state;
    private boolean isVisited;
    private List<GraphNode<T>> adjacentNodes;
    private List<GraphEdge<T>> adjacentEdges; // needed for Graph problems having distance/edge weight

    public GraphNode(T d) {
        data = d;
        state = VisitedState.UNVISITED;
        isVisited = false;
        adjacentNodes = new ArrayList<>();
        adjacentEdges = new ArrayList<>();
    }

    public T data() {
        return data;
    }
    public void setData(T d) {
        data = d;
    }

    public VisitedState getState() {
        return state;
    }
    public void setState(VisitedState s) {
        state = s;
    }

    public List<GraphNode<T>> getAdjacentNodes() {
        return adjacentNodes;
    }
    public void setAdjacentNodes(List<GraphNode<T>> a) {
        adjacentNodes = a;
    }

    public boolean getIsVisited() {
        return isVisited;
    }
    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public List<GraphEdge<T>> getAdjacentEdges() {
        return this.adjacentEdges;
    }

    public void setAdjacentEdges(List<GraphEdge<T>> adjacentEdges) {
        this.adjacentEdges = adjacentEdges;
    }
}
