package service;

import util.Printer;

/**
 * https://www.lintcode.com/problem/515/
 * https://www.youtube.com/watch?v=-w67-4tnH5U
 */
public class PaintHouseSolution {
    public static void invoke() {
        // prints 10
        int[][] costs1 = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        Printer.println(computeMinCostToPaintHouses(costs1));

        // prints 18
        int[][] costs2 = {{1, 2, 17}, {16, 16, 5}, {2000, 2000, 1}};
        Printer.println(computeMinCostToPaintHouses(costs2));
    }

    private static int computeMinCostToPaintHouses(int[][] costs) {
      // input validation
      if (costs == null || costs.length == 0) {
          return 0;
      }

      int numHouses = costs.length;
      int[][] minCostsDP = new int[numHouses][3];

      // base case
      // fill out the first row of the dp array
      minCostsDP[0][0] = costs[0][0];
      minCostsDP[0][1] = costs[0][1];
      minCostsDP[0][2] = costs[0][2];

      for (int i = 1; i < numHouses; i ++) {
          minCostsDP[i][0] = costs[i][0] + Math.min(minCostsDP[i - 1][1], minCostsDP[i - 1][2]);
          minCostsDP[i][1] = costs[i][1] + Math.min(minCostsDP[i - 1][0], minCostsDP[i - 1][2]);
          minCostsDP[i][2] = costs[i][2] + Math.min(minCostsDP[i - 1][0], minCostsDP[i - 1][1]);
      }

      // minimum among the elements of the last row
      return Math.min(Math.min(minCostsDP[numHouses - 1][0], minCostsDP[numHouses - 1][1]), minCostsDP[numHouses - 1][2]);
    }
}
