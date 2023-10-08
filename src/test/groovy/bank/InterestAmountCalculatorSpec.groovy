package bank

import com.reylibutan.lvluptdd.bank.InterestAmountCalculator
import spock.lang.Specification

class InterestAmountCalculatorSpec extends Specification {

  def interestAmountCalculator

  void setup() {
    interestAmountCalculator = new InterestAmountCalculator()
  }

  def "should throw #expectedException when investment amount is invalid (#investmentAmount)"() {
    when:
    interestAmountCalculator.calcInterestAmt(investmentAmount)

    then:
    def error = thrown(expectedException)
    error.message == errorMessage

    where:
    investmentAmount          || expectedException        | errorMessage
    null                      || IllegalArgumentException | "Null or negative amounts are not supported in this interest calculation."
    new BigDecimal("-9")      || IllegalArgumentException | "Null or negative amounts are not supported in this interest calculation."
    new BigDecimal("-123213") || IllegalArgumentException | "Null or negative amounts are not supported in this interest calculation."
  }

  def "should return 0 interest amount when investment amount is 0"() {
    given:
    var investmentAmount = 0

    when:
    var interestAmount = interestAmountCalculator.calcInterestAmt(investmentAmount)

    then:
    interestAmount == 0
  }
}
