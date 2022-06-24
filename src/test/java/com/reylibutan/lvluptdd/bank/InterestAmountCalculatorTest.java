package com.reylibutan.lvluptdd.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class InterestAmountCalculatorTest {

  private InterestAmountCalculator interestAmountCalculator;

  @BeforeEach
  public void setup() {
    interestAmountCalculator = new InterestAmountCalculator();
  }

  @Test
  @DisplayName("Null investment amount should throw IllegalArgumentException")
  public void calcInterestAmt_withNullAmt_shouldThrowIllegalArgsEx() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> interestAmountCalculator.calcInterestAmt(null));
  }

  @Test
  @DisplayName("Negative investment amounts should throw IllegalArgumentException")
  public void calcInterestAmt_withNegativeInvestmentAmt_shouldThrowIllegalArgsEx() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> interestAmountCalculator.calcInterestAmt(new BigDecimal("-12312.00")));
  }

  @Test
  @DisplayName("Zero Investment amount should return zero interest amount")
  public void calcInterestAmt_withZeroInvestmentAmt_shouldReturnZero() {
    assertThat(getInterestAmtFromNumStr("0")).isEqualByComparingTo(ZERO);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/investments_small.csv")
  @DisplayName("Investment amounts > 0 and < 10,000 should return 1% interest amount")
  public void calcInterestAmt_withSmallInvestmentAmt_shouldReturnSmallInterestAmt(
      String investment, String expectedInterestAmt) {
    assertThat(getInterestAmtFromNumStr(investment))
        .isEqualByComparingTo(new BigDecimal(expectedInterestAmt));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/investments_medium.csv")
  @DisplayName("Investment amounts >= 10,000 and < 25,000 should return 1.5% interest amount")
  public void calcInterestAmt_withMedInvestmentAmt_shouldReturnMedInterestAmt(
      String investment, String expectedInterestAmt) {
    assertThat(getInterestAmtFromNumStr(investment))
        .isEqualByComparingTo(new BigDecimal(expectedInterestAmt));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/investments_big.csv")
  @DisplayName("Investment amounts >= 25,000 should return 1.75% interest amount")
  public void calcInterestAmt_withBigInvestmentAmt_shouldReturnBigInterestAmt(
      String investment, String expectedInterestAmt) {
    assertThat(getInterestAmtFromNumStr(investment))
        .isEqualByComparingTo(new BigDecimal(expectedInterestAmt));
  }

  @Test
  @DisplayName(
      "Very large investment amounts should still calculate using the highest interest rate")
  public void calcInterestAmt_withVeryLargeInvestmentAmt_shouldSucceed() {
    assertThat(getInterestAmtFromNumStr("92312736263.22"))
        .isEqualByComparingTo(new BigDecimal("1615472884.61"));
  }

  private BigDecimal getInterestAmtFromNumStr(String numStr) {
    return interestAmountCalculator.calcInterestAmt(new BigDecimal(numStr));
  }
}
