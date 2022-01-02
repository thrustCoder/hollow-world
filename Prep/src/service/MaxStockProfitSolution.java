package service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import model.stock.BuyAndSellStockProfit;
import model.stock.StockPrice;
import util.Printer;

/**
 * Given a list of stock prices and their dates represented by instants,
 * return the buy and sell instants such that we make maximum profit.
 */
public class MaxStockProfitSolution {

  public static void invoke() {
    List<StockPrice> stockPrices;

    // Testcase 1 - When single element, should print first element
    stockPrices = Arrays.asList(
      new StockPrice(Instant.parse("2022-01-01T00:00:00Z"), BigDecimal.valueOf(7.87)));
    // Prints { buyInstant='2022-01-01T00:00:00Z', sellInstant='2022-01-01T00:00:00Z', profit='0'}
    Printer.println(computeMaxStockProfit(stockPrices));

    // Testcase 2 - When valid list, should print max profit
    stockPrices = Arrays.asList(
      new StockPrice(Instant.parse("2022-01-01T00:00:00Z"), BigDecimal.valueOf(7.87)),
      new StockPrice(Instant.parse("2022-01-02T00:00:00Z"), BigDecimal.valueOf(8.93)),
      new StockPrice(Instant.parse("2022-01-02T01:00:00Z"), BigDecimal.valueOf(3)),
      new StockPrice(Instant.parse("2022-01-02T02:00:00Z"), BigDecimal.valueOf(5.67)),
      new StockPrice(Instant.parse("2022-01-05T03:00:00Z"), BigDecimal.valueOf(1.609)));
    // Prints: { buyInstant='2022-01-02T01:00:00Z', sellInstant='2022-01-02T02:00:00Z', profit='2.67'}
    Printer.println(computeMaxStockProfit(stockPrices));

    // Testcase 3 - When stricly decreasing list, should print first element
    stockPrices = Arrays.asList(
      new StockPrice(Instant.parse("2022-01-01T00:00:00Z"), BigDecimal.valueOf(7.87)),
      new StockPrice(Instant.parse("2022-01-02T00:00:00Z"), BigDecimal.valueOf(6.93)),
      new StockPrice(Instant.parse("2022-01-02T01:00:00Z"), BigDecimal.valueOf(5.67)),
      new StockPrice(Instant.parse("2022-01-02T02:00:00Z"), BigDecimal.valueOf(4.67)),
      new StockPrice(Instant.parse("2022-01-05T03:00:00Z"), BigDecimal.valueOf(1.609)));
    // Prints: { buyInstant='2022-01-01T00:00:00Z', sellInstant='2022-01-01T00:00:00Z', profit='0'}
    Printer.println(computeMaxStockProfit(stockPrices));

    // Testcase 4 - When equal stock prices, should print first element
    stockPrices = Arrays.asList(
      new StockPrice(Instant.parse("2022-01-01T00:00:00Z"), BigDecimal.valueOf(7.87)),
      new StockPrice(Instant.parse("2022-01-02T00:00:00Z"), BigDecimal.valueOf(7.87)),
      new StockPrice(Instant.parse("2022-01-03T01:00:00Z"), BigDecimal.valueOf(7.87)));
    // Prints: { buyInstant='2022-01-01T00:00:00Z', sellInstant='2022-01-01T00:00:00Z', profit='0'}
    Printer.println(computeMaxStockProfit(stockPrices));
  }

  /**
   * An brute force O(n^2) solution would be to compare each element with the other in a nested loop and return the overall max profit.
   * This however, is the O(n) solution for this problem.
   * Solution inspired by: https://www.youtube.com/watch?v=mj7N8pLCJ6w
   *
   * Iterate over the loop once.
   * If the current stock value is the lowest seen so far, update *overall minimum stock value*.
   * This simulates potential buying of the stock at it's minimum price.
   * We then immediately continue to the next iteration because we cannot buy and sell on the same iteration.
   * Otherwise, compute the current profit with the minimum seen so far and update *overall max profit* if needed.
   * This simulates potential selling of the stock price at maximum profit.
   *
   * @param stockPrices List of stock prices and their instants.
   * @return            Buy and sell instants corresponding to the maximium profit.
   */
  private static BuyAndSellStockProfit computeMaxStockProfit(List<StockPrice> stockPrices) {

    // input validation
    if (stockPrices == null || stockPrices.isEmpty()) {
      throw new IllegalArgumentException("stockPrices cannot be empty");
    }

    BuyAndSellStockProfit maxStockProfit = null;

    // initialize maxStockProfit with the first element's instants and zero profit
    // this will act as our default return value in case the list is non-increasing.
    StockPrice firstEle = stockPrices.get(0);
    if (firstEle != null) {
      maxStockProfit = new BuyAndSellStockProfit(firstEle.getInstant(), firstEle.getInstant(), BigDecimal.ZERO);
    }

    BigDecimal overallMaxProfit = BigDecimal.valueOf(Double.MIN_VALUE);
    StockPrice overallMinStockPrice = new StockPrice(null, BigDecimal.valueOf(Double.MAX_VALUE));

    for (int i = 0; i < stockPrices.size(); i ++) {

      StockPrice currStockPrice = stockPrices.get(i);

      if (currStockPrice != null && currStockPrice.getValue() != null) {

        // this is the BigDecimal way of writing: if (currStockPrice.getValue() < overallMinStockPrice.getValue())
        if (currStockPrice.getValue().compareTo(overallMinStockPrice.getValue()) < 0) {
          overallMinStockPrice.setValue(currStockPrice.getValue());
          overallMinStockPrice.setInstant(currStockPrice.getInstant());

        } else {
          BigDecimal currProfit = currStockPrice.getValue().subtract(overallMinStockPrice.getValue());

          // This is the BigDecimal way of writing: if (currProfit > overallMaxProfit && currProfit > 0)
          // Note, currProfit > 0 helps us to NOT update overallMaxProfit when the list is decreasing or equal.
          if (currProfit.compareTo(overallMaxProfit) > 0 && currProfit.compareTo(BigDecimal.ZERO) > 0) {
            overallMaxProfit = currProfit;

            maxStockProfit.setbuyInstant(overallMinStockPrice.getInstant());
            maxStockProfit.setsellInstant(currStockPrice.getInstant());
            maxStockProfit.setProfit(overallMaxProfit);
          }
        }
      }
    }

    return maxStockProfit;
  }
}
