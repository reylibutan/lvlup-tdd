package com.reylibutan.lvluptdd.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InvestmentCalculatorTest {

  private final int SCALE = 2;

  private InvestmentCalculator investmentCalculator = new InvestmentCalculator();

  @Test
  public void givenNegativeAmount_whenCalculated_thenShouldThrowException() {
    BigDecimal amount = new BigDecimal(-1);
    Exception e =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              BigDecimal actualInterestAmount = investmentCalculator.calculateInterest(amount);
            });
  }

  @Test
  public void givenZeroAmount_whenCalculated_thenShouldReturnZero() {
    BigDecimal amount = new BigDecimal(0);
    BigDecimal actualInterestAmount = investmentCalculator.calculateInterest(amount);

    BigDecimal expectedInterestAmount = new BigDecimal("0.00");
    expectedInterestAmount.setScale(SCALE, DOWN);

    assertEquals(expectedInterestAmount, actualInterestAmount);
  }

  @Test
  public void givenSmallAmount_whenCalculated_thenShouldReturnInterest() {
    BigDecimal amount = new BigDecimal(10000);
    BigDecimal actualInterestAmount = investmentCalculator.calculateInterest(amount);

    BigDecimal expectedInterestAmount = new BigDecimal("100.00");
    expectedInterestAmount.setScale(SCALE, DOWN);

    assertEquals(expectedInterestAmount, actualInterestAmount);
  }

  @Test
  public void givenMedAmount_whenCalculated_thenShouldReturnInterest() {
    BigDecimal amount = new BigDecimal(15000);
    BigDecimal actualInterestAmount = investmentCalculator.calculateInterest(amount);

    BigDecimal expectedInterestAmount = new BigDecimal("225.00");

    assertEquals(expectedInterestAmount, actualInterestAmount);
  }

  @Test
  public void givenLargeAmount_whenCalculated_thenShouldReturnInterest() {
    BigDecimal amount = new BigDecimal(50000);
    BigDecimal actualInterestAmount = investmentCalculator.calculateInterest(amount);

    BigDecimal expectedInterestAmount = new BigDecimal("875.00");

    assertEquals(expectedInterestAmount, actualInterestAmount);
  }
}
