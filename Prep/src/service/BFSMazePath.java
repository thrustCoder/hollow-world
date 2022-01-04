package service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import model.Cell;
import model.Grid;
import model.VisitedState;
import util.Printer;

/**
 * Find the length of the shortest path in a binary maze, given start and destination cells.
 * 1 means path exists, 0 means no path.
 * https://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 */
public class BFSMazePath {

    public static void invoke() {
        final int maze[][] =
               {{1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}};

        final Cell source = new Cell(0, 0);

        Cell dest = new Cell(3, 4);
        // Prints 11
        Printer.println(BFS(maze, source, dest));

        dest = new Cell(0, 0);
        // Prints 0
        Printer.println(BFS(maze, source, dest));

        dest = new Cell(1, 0);
        // Prints 1
        Printer.println(BFS(maze, source, dest));

        dest = new Cell(0, 1);
        // Prints -1
        Printer.println(BFS(maze, source, dest));

        dest = new Cell(4, 6);
        // Prints 14
        Printer.println(BFS(maze, source, dest));
    }


    /**
     * Time complexity: O(n*m) -- because there are a total of nm elements in this matrix
     * And in the worst case you may have to insert each one into the Queue
     * The work that's done on each element (e.g. dequeing, setting visited state etc.)
     * takes constant time. So overall the time complexity is O(n*m)
     */
    private static int BFS(final int[][] maze, final Cell source, final Cell dest) {

        // input validation

        if (maze == null || maze.length == 0) {
            return -1;
        }

        final Grid grid = new Grid(maze);

        final int sourceX = source.getX();
        final int sourceY = source.getY();
        final int destX = dest.getX();
        final int destY = dest.getY();

        if (!grid.areCoordsWithinBounds(sourceX, sourceY) || !grid.areCoordsWithinBounds(destX, destY)) {
            return -1;
        }

        if (sourceX == destX && sourceY == destY) {
            return 0;
        }

        final Cell sourceCell = grid.getCellMatrix()[sourceX][sourceY];

        final Queue<Cell> traversalQ = new LinkedList<>();

        // add source to queue and set VISITING state and distance from source.
        traversalQ.add(sourceCell);
        // can also just use a boolean isVisited flag
        sourceCell.setVisitedState(VisitedState.VISITING);
        sourceCell.setDistFromSource(0);

        while (!traversalQ.isEmpty()) {

            final Cell currCell = traversalQ.poll();

            for (final Cell validDirection : getValidDirections()) {
                final int adjX = currCell.getX() + validDirection.getX();
                final int adjY = currCell.getY() + validDirection.getY();

                if (grid.areCoordsWithinBounds(adjX, adjY)) {

                    final Cell adjCell = grid.getCellMatrix()[adjX][adjY];

                    if (adjCell.isReachable() && VisitedState.UNVISITED.equals(adjCell.getVisitedState())) {
                        traversalQ.add(adjCell);
                        adjCell.setDistFromSource(currCell.getDistFromSource() + 1);
                        adjCell.setVisitedState(VisitedState.VISITING);

                        if (adjCell.getX() == destX && adjCell.getY() == destY) {
                            return adjCell.getDistFromSource();
                        }
                    }
                }
            }

            currCell.setVisitedState(VisitedState.VISITED);
        }

        // no path b/w source and dest
        return -1;
    }

    private static Set<Cell> getValidDirections() {
        final Set<Cell> validDirections = new HashSet<>();

        validDirections.add(new Cell(-1, 0));
        validDirections.add(new Cell(1, 0));
        validDirections.add(new Cell(0, -1));
        validDirections.add(new Cell(0, 1));

        return validDirections;
    }

}
