package com.cg.cqrs.common.customer;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.cg.cqrs.common.domain.Money;

public class CustomerCreditReservedEvent implements CustomerEvent {
  private String orderId;
  private Money orderTotal;

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  public CustomerCreditReservedEvent() {
  }

  public CustomerCreditReservedEvent(String orderId, Money orderTotal) {
    this.orderId = orderId;
    this.orderTotal = orderTotal;
  }

  public String getOrderId() {
    return orderId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }
}
