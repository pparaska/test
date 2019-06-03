package com.cg.cqrs.orderscommmon;

import com.cg.cqrs.common.domain.Money;

public class CreateOrderRequest {
  private Money orderTotal;
  private String customerId;

  public CreateOrderRequest() {
  }

  public CreateOrderRequest(String customerId, Money orderTotal) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public String getCustomerId() {
    return customerId;
  }
}
