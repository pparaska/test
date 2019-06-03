package com.cg.cqrs.customersservice.backend;

import com.cg.cqrs.common.domain.Money;

public class CreateCustomerCommand implements CustomerCommand {
  private final String name;
  private final Money creditLimit;

  public CreateCustomerCommand(String name, Money creditLimit) {
    this.name = name;
    this.creditLimit = creditLimit;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }

  public String getName() {
    return name;
  }
}
