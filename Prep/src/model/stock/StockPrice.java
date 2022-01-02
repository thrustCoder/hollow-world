package model.stock;

import java.math.BigDecimal;
import java.time.Instant;

public class StockPrice {
  private Instant date;
  private BigDecimal value;

  public StockPrice(Instant date, BigDecimal value) {
    this.date = date;
    this.value = value;
  }

  public Instant getDate() {
    return this.date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public BigDecimal getValue() {
    return this.value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }
}
