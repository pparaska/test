package com.cg.cqrs.customersservice.backend;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.List;

import com.cg.cqrs.common.customer.CustomerCreatedEvent;
import com.cg.cqrs.common.customer.CustomerCreditLimitExceededEvent;
import com.cg.cqrs.common.customer.CustomerCreditReservedEvent;
import com.cg.cqrs.common.domain.Money;

public class Customer extends ReflectiveMutableCommandProcessingAggregate<Customer, CustomerCommand> {

  private ReservedCreditTracker reservedCreditTracker;

  private Money creditLimit;
  private String name;

  public Money availableCredit() {
    return creditLimit.subtract(reservedCreditTracker.reservedCredit());
  }

  public Money getCreditLimit() {
    return creditLimit;
  }

  public List<Event> process(CreateCustomerCommand cmd) {
    return EventUtil.events(new CustomerCreatedEvent(cmd.getName(), cmd.getCreditLimit()));
  }

  public List<Event> process(ReserveCreditCommand cmd) {
    if (availableCredit().isGreaterThanOrEqual(cmd.getOrderTotal()))
      return EventUtil.events(new CustomerCreditReservedEvent(cmd.getOrderId(), cmd.getOrderTotal()));
    else
      return EventUtil.events(new CustomerCreditLimitExceededEvent(cmd.getOrderId()));
  }


  public void apply(CustomerCreatedEvent event) {
    this.name = name;
    this.creditLimit = event.getCreditLimit();
    this.reservedCreditTracker = new ReservedCreditTracker();
  }

  public void apply(CustomerCreditReservedEvent event) {
    reservedCreditTracker.addReservation(event.getOrderId(), event.getOrderTotal());
  }

  public void apply(CustomerCreditLimitExceededEvent event) {
    // Do nothing
  }


  public String getName() {
    return name;
  }
}



