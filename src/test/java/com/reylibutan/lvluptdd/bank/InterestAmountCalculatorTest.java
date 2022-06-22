package com.reylibutan.lvluptdd.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InterestAmountCalculatorTest {

  private final InterestAmountCalculator investmentCalculator = new InterestAmountCalculator();

  @Test
  @DisplayName("Null investment amount should throw IllegalArgumentException")
  public void calcInterestAmt_withNullAmt_shouldThrowIllegalArgsEx() {
    assertThrows(IllegalArgumentException.class, () -> investmentCalculator.calcInterestAmt(null));
  }

  @Test
  @DisplayName("Negative investment amounts should throw IllegalArgumentException")
  public void calcInterestAmt_withNegativeInvestmentAmt_shouldThrowIllegalArgsEx() {
    assertThrows(
        IllegalArgumentException.class,
        () -> investmentCalculator.calcInterestAmt(new BigDecimal("-12312.00")));
  }

  @Test
  @DisplayName("Zero Investment amount should return zero interest amount")
  public void calcInterestAmt_withZeroInvestmentAmt_shouldReturnZero() {
    assertEqualAmounts(getInterestAmtFromNumStr("0"), ZERO);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/investments_small.csv")
  @DisplayName("Investment amounts > 0 and < 10,000 should return 1% interest amount")
  public void calcInterestAmt_withSmallInvestmentAmt_shouldReturnSmallInterestAmt(
      String investment, String expectedInterestAmt) {
    assertEqualAmounts(getInterestAmtFromNumStr(investment), new BigDecimal(expectedInterestAmt));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/investments_medium.csv")
  @DisplayName("Investment amounts >= 10,000 and < 25,000 should return 1.5% interest amount")
  public void calcInterestAmt_withMedInvestmentAmt_shouldReturnMedInterestAmt(
      String investment, String expectedInterestAmt) {
    assertEqualAmounts(getInterestAmtFromNumStr(investment), new BigDecimal(expectedInterestAmt));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/investments_big.csv")
  @DisplayName("Investment amounts >= 25,000 should return 1.75% interest amount")
  public void calcInterestAmt_withBigInvestmentAmt_shouldReturnBigInterestAmt(
      String investment, String expectedInterestAmt) {
    assertEqualAmounts(getInterestAmtFromNumStr(investment), new BigDecimal(expectedInterestAmt));
  }

  @Test
  @DisplayName(
      "Very large investment amounts should still calculate using the highest interest rate")
  public void calcInterestAmt_withVeryLargeInvestmentAmt_shouldSucceed() {
    assertEqualAmounts(getInterestAmtFromNumStr("92312736263.22"), new BigDecimal("1615472884.61"));
  }

  private BigDecimal getInterestAmtFromNumStr(String numStr) {
    return investmentCalculator.calcInterestAmt(new BigDecimal(numStr));
  }

  private void assertEqualAmounts(BigDecimal actual, BigDecimal expected) {
    // TODO: this should use AssertJ isEqualByComparingTo
    // the issue of using just equals() is that it also compares scale
    // we need to compare BigDecimals' values regardless of scale (ex. 1.1 should be equal to
    // 1.10000)
    assertEquals(expected, actual, "Provided amounts are not equal");
  }
}
