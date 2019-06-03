package com.cg.cqrs.ordershistorycommon;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cg.cqrs.common.domain.Money;

import java.util.HashMap;
import java.util.Map;

@Document
public class CustomerView {

  @Id
  private String id;


  private Map<String, OrderInfo> orders = new HashMap<>();
  private String name;
  private Money creditLimit;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public Map<String, OrderInfo> getOrders() {
    return orders;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setCreditLimit(Money creditLimit) {
    this.creditLimit = creditLimit;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }
}
