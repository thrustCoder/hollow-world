package model;

public class Cell {
    private int x;
    private int y;
    private Integer data;
    private Integer distFromSource;
    private VisitedState visitedState;

    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(final int x, final int y, final Integer data, final VisitedState visitedState) {
        this.x = x;
        this.y = y;
        this.data = data;
        this.visitedState = visitedState;
    }

    public Cell(final int x, final int y, final Integer data, final VisitedState visitedState, final Integer distFromSource) {
        this.x = x;
        this.y = y;
        this.data = data;
        this.visitedState = visitedState;
        this.distFromSource = distFromSource;
    }

    public boolean isReachable() {
        return data == 1;
    }

    // Getters

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Integer getData() {
        return this.data;
    }

    public VisitedState getVisitedState() {
        return this.visitedState;
    }

    // Setters

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setData(final Integer data) {
        this.data = data;
    }

    public Integer getDistFromSource() {
        return distFromSource;
    }

    public void setDistFromSource(Integer distFromSource) {
        this.distFromSource = distFromSource;
    }

    public void setVisitedState(VisitedState visitedState) {
        this.visitedState = visitedState;
    }
}
