package com.reylibutan.lvluptdd.bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.reylibutan.lvluptdd.bank.NumberUtil.isLessThan;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Collections.emptyList;

// TODO expose via an interface later?
public class InterestAmountCalculator {

  private List<InterestAmountThreshold> interestRates = emptyList();

  public InterestAmountCalculator() {
    initInterestRateTable();
  }

  /**
   * Computes the interest rate amount given an investment amount {@code investmentAmt}. <br>
   * Interest rate thresholds can be defined in a CSV file in {@code
   * src/main/resources/interest_rates.csv}. <br>
   * Example content is below. 1st column is the threshold and the 2nd column is the interest rate
   * (1 = 100%). <br>
   * Interest rate should be in numerical order.
   *
   * <pre>
   *    0, 0.01
   *    10000, 0.015
   *    25000, 0.0175
   * </pre>
   *
   * @param investmentAmt investment amount
   * @return the calculated investment interest amount
   */
  public BigDecimal calcInterestAmt(BigDecimal investmentAmt) {
    if (investmentAmt == null || isLessThan(investmentAmt, ZERO)) {
      throw new IllegalArgumentException("Null or negative amounts are not supported in this interest calculation.");
    }

    BigDecimal interestRate = ZERO;
    for (InterestAmountThreshold currRate : interestRates) {
      if (isLessThan(investmentAmt, currRate.getAmount())) {
        break;
      } else {
        interestRate = currRate.getInterestRate();
      }
    }

    return investmentAmt.multiply(interestRate).setScale(investmentAmt.scale(), HALF_UP);
  }

  private void initInterestRateTable() {
    interestRates = new ArrayList<>();

    String filePath = "src/main/resources/interest_rates.csv";
    Path path = Paths.get(filePath);
    try (Stream<String> lines = Files.lines(path)) {
      // TODO: better exception handling here especially for input errors (ex. non-numbers)
      lines.forEach(l -> {
        String[] cols = l.split(",");
        interestRates.add(new InterestAmountThreshold(new BigDecimal(cols[0].trim()), new BigDecimal(cols[1].trim())));
      });
    } catch (IOException e) {
      // TODO: log here
      throw new RuntimeException("Unable to read file=({" + filePath + "})", e);
    }
  }
}
