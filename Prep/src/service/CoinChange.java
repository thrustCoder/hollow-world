package service;

import util.Printer;

/**
 * An alternative way to solve Coin change problem in Gayle Laakmann's Recursion and DP chapter.
 * Given an infinite no. of quarters (25), dimes (10), nickels (5) and pennies (1), write code to calculate the no. of ways
 * of representing n cents.
 */
public class CoinChange {

    /**
     * E.g.:
     * 11 = 1 + 1 + 1... + 1
     *    = 1 + 1 + 1 + 1 + 1 + 1 + 5
     *    = 1 + 5 + 5
     *    = 1 + 10
     *
     *    should return 4.
     */
    public static void makeChange() {
        final int[] coins = new int[] {1, 5, 10, 25};
        final int n = 11;

        Printer.print(computeChangeWays(coins, n));
    }

    public static int computeChangeWays(int[] coins, int n) {

        // input validation
        if (n == 0) {
            // no. of ways of making 0 with any set of coins is 0.
            return 0;
        }

        int[] dpChangeWays = new int[n + 1];

        // base case
        dpChangeWays[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            // if current change coin is greater than the amount, doesn't make sense to continue further.
            // we can safely break out at this point, since coins[] is sorted.
            final int currentCoin = coins[i];
            if (currentCoin > n) {
                break;
            }
            for (int j = 1; j <= n; j++) {
                if (j >= currentCoin) {
                    // no. of ways of change = no. of ways of change using the previous coin + no. of ways of changing [j - currentCoin]
                    // e.g. if n = 11 and currentCoin = 10,
                    // dp[11] = dp[11] (computed with the previous coin, i.e. 5)
                    //          + dp[1] (#ways of changing 1 cent (since changing 11 with 10 cent coin is effectively dp[1])
                    dpChangeWays[j] = dpChangeWays[j] + dpChangeWays[j - currentCoin];
                }
            }
        }
        return dpChangeWays[n];
    }
}
