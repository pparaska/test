package com.cg.cqrs.orderscommmon;


public class CreateOrderResponse {
  private String orderId;

  public CreateOrderResponse() {
  }

  public CreateOrderResponse(String orderId) {
    this.orderId = orderId;

  }

  public String getOrderId() {
    return orderId;
  }
}