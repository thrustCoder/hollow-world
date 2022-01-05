package model;

public class GraphEdge<T> {
  private GraphNode<T> node;
  private Integer edgeWeight;

  public GraphEdge(GraphNode<T> node, Integer weight) {
    this.node = node;
    this.edgeWeight = weight;
  }

  // Getters and setters

  public GraphNode<T> getNode() {
    return this.node;
  }

  public void setNode(GraphNode<T> adjNode) {
    this.node = adjNode;
  }

  public Integer getEdgeWeight() {
    return this.edgeWeight;
  }

  public void setEdgeWeight(Integer weight) {
    this.edgeWeight = weight;
  }
}
