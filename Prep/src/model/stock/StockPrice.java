package model.stock;

import java.math.BigDecimal;
import java.time.Instant;

public class StockPrice {
  private Instant instant;
  private BigDecimal value;

  public StockPrice(Instant instant, BigDecimal value) {
    this.instant = instant;
    this.value = value;
  }

  // Getters and setters

  public Instant getInstant() {
    return this.instant;
  }

  public void setInstant(Instant date) {
    this.instant = date;
  }

  public BigDecimal getValue() {
    return this.value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }
}
