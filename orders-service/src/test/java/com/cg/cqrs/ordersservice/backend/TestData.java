package com.cg.cqrs.ordersservice.backend;

import com.cg.cqrs.common.domain.Money;

public class TestData {
  static String customerId = "1223232";
  static String nonExistentCustomerId = "1223232-none";

  static Money orderTotal = new Money((int) (System.currentTimeMillis() % 1204));
}
