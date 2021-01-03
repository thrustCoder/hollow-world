package model;

public class Grid {
    private int[][] matrix;
    private VisitedState[][] visited;
    private int numRows;
    private int numCols;

    public Grid(final int[][] matrix) {
        this.matrix = matrix;
        this.numRows = matrix.length;
        this.numCols = matrix[0].length;

        this.visited = new VisitedState[numRows][numCols];
        // set each entry to false
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.visited[i][j] = VisitedState.UNVISITED;
            }
        }

    }

    public boolean areCoordsWithinBounds(final int x, final int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols;
    }

    // Getters and Setters

    public int[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public VisitedState[][] getVisited() {
        return visited;
    }

    public void setVisited(VisitedState[][] visited) {
        this.visited = visited;
    }
}
