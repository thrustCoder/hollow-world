package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpsin on 10/16/2016.
 */
public class GraphNode<T> {
    private T data;
    private VisitedState state;
    private List<GraphNode<T>> adjacentNodes;

    public GraphNode(T d) {
        data = d;
        state = VisitedState.UNVISITED;
        adjacentNodes = new ArrayList<>();
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
}
