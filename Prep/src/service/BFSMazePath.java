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

    private static int BFS(final int[][] maze, final Cell source, final Cell dest) {

        final int sourceX = source.getX();
        final int sourceY = source.getY();
        final int destX = dest.getX();
        final int destY = dest.getY();

        // input validation
        if (maze[sourceX][sourceY] != 1 || maze[destX][destY] != 1) {
            return -1;
        }

        final Cell sourceCell = new Cell(sourceX, sourceY, maze[sourceX][sourceY], 0);

        final Grid grid = new Grid(maze);
        final VisitedState[][] visited = grid.getVisited();

        final Queue<Cell> traversalQ = new LinkedList<>();

        // add source to queue and set VISITING state
        visited[sourceX][sourceY] = VisitedState.VISITING;
        traversalQ.add(sourceCell);

        while (!traversalQ.isEmpty()) {

            final Cell currCell = traversalQ.poll();

            if (currCell.getX() == destX && currCell.getY() == destY) {
                return currCell.getDistFromSource();
            }

            for (final Cell validDirection : getValidDirections()) {
                final int adjX = currCell.getX() + validDirection.getX();
                final int adjY = currCell.getY() + validDirection.getY();

                if (grid.areCoordsWithinBounds(adjX, adjY)) {

                    final Cell adjCell = new Cell(adjX, adjY, maze[adjX][adjY], currCell.getDistFromSource() + 1);

                    if (adjCell.isReachable() && VisitedState.UNVISITED.equals(visited[adjX][adjY])) {
                        traversalQ.add(adjCell);
                        visited[adjX][adjY] = VisitedState.VISITING;
                    }
                }
            }
            visited[currCell.getX()][currCell.getY()] = VisitedState.VISITED;
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
