package com.reylibutan.lvluptdd.bank;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InterestAmountThreshold {
  private final BigDecimal amount;
  private final BigDecimal interestRate;
}
