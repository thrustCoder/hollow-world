package model;

public class Grid {
    private int[][] matrix;
    private Cell[][] cellMatrix;
    private int numRows;
    private int numCols;

    public Grid(final int[][] matrix) {
        this.matrix = matrix;
        numRows = matrix.length;
        numCols = matrix[0].length;

        // initialize and populate cellMatrix
        cellMatrix = new Cell[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                cellMatrix[i][j] = new Cell(i, j, matrix[i][j], VisitedState.UNVISITED, -1);
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

    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    public void setCellMatrix(Cell[][] cellMatrix) {
        this.cellMatrix = cellMatrix;
    }
}
