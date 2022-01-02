package model.stock;

import java.math.BigDecimal;
import java.time.Instant;

public class BuyAndSellStockProfit {
  private Instant buyInstant;
  private Instant sellInstant;
  private BigDecimal profit;

  public BuyAndSellStockProfit(Instant buyInstant, Instant sellInstant, BigDecimal profit) {
    this.buyInstant = buyInstant;
    this.sellInstant = sellInstant;
    this.profit = profit;
  }

  @Override
  public String toString() {
    return "{" +
      " buyInstant='" + getbuyInstant() + "'" +
      ", sellInstant='" + getsellInstant() + "'" +
      ", profit='" + getProfit() + "'" +
      "}";
  }

  // Getters and setters

  public BigDecimal getProfit() {
    return this.profit;
  }

  public void setProfit(BigDecimal profit) {
    this.profit = profit;
  }

  public Instant getbuyInstant() {
    return this.buyInstant;
  }

  public void setbuyInstant(Instant buyInstant) {
    this.buyInstant = buyInstant;
  }

  public Instant getsellInstant() {
    return this.sellInstant;
  }

  public void setsellInstant(Instant sellInstant) {
    this.sellInstant = sellInstant;
  }
}
