package com.reylibutan.lvluptdd.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvestmentCalculator {

  private final int SCALE = 2;
  private final BigDecimal AMOUNT_SMALL = new BigDecimal(10_000);
  private final BigDecimal AMOUNT_MED = new BigDecimal(25_000);
  private final BigDecimal INTEREST_RATE_SMALL = new BigDecimal(0.01);
  private final BigDecimal INTEREST_RATE_MED = new BigDecimal(0.015);
  private final BigDecimal INTEREST_RATE_LARGE = new BigDecimal(0.0175);

  public BigDecimal calculateInterest(BigDecimal amount) {

    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException();
    } else if (amount.compareTo(AMOUNT_SMALL) <= 0) {
      return amount.multiply(INTEREST_RATE_SMALL).setScale(SCALE, RoundingMode.FLOOR);
    } else if (amount.compareTo(AMOUNT_SMALL) == 1 && amount.compareTo(AMOUNT_MED) <= 0) {
      return amount.multiply(INTEREST_RATE_MED).setScale(SCALE, RoundingMode.FLOOR);
    } else {
      return amount.multiply(INTEREST_RATE_LARGE).setScale(SCALE, RoundingMode.FLOOR);
    }
  }
}
