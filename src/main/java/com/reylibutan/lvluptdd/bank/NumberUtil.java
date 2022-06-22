package com.reylibutan.lvluptdd.bank;

import java.math.BigDecimal;

public class NumberUtil {

  public static boolean isLessThan(BigDecimal first, BigDecimal second) {
    return first.compareTo(second) < 0;
  }

  public static boolean isGreaterThan(BigDecimal first, BigDecimal second) {
    return first.compareTo(second) > 0;
  }

  public static boolean isEqualTo(BigDecimal first, BigDecimal second) {
    return first.compareTo(second) == 0;
  }

  public static boolean isLessOrEqualTo(BigDecimal first, BigDecimal second) {
    return first.compareTo(second) <= 0;
  }

  public static boolean isGreaterOrEqualTo(BigDecimal first, BigDecimal second) {
    return first.compareTo(second) >= 0;
  }
}
