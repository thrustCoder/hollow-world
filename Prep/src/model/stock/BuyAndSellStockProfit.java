package model.stock;

import java.math.BigDecimal;
import java.time.Instant;

public class BuyAndSellStockProfit {
  private Instant buyDate;
  private Instant sellDate;
  private BigDecimal profit;

  public BuyAndSellStockProfit(Instant buyDate, Instant sellDate, BigDecimal profit) {
    this.buyDate = buyDate;
    this.sellDate = sellDate;
    this.profit = profit;
  }

  @Override
  public String toString() {
    return "{" +
      " buyDate='" + getBuyDate() + "'" +
      ", sellDate='" + getSellDate() + "'" +
      ", profit='" + getProfit() + "'" +
      "}";
  }

  public BigDecimal getProfit() {
    return this.profit;
  }

  public void setProfit(BigDecimal profit) {
    this.profit = profit;
  }

  public Instant getBuyDate() {
    return this.buyDate;
  }

  public void setBuyDate(Instant buyDate) {
    this.buyDate = buyDate;
  }

  public Instant getSellDate() {
    return this.sellDate;
  }

  public void setSellDate(Instant sellDate) {
    this.sellDate = sellDate;
  }
}
