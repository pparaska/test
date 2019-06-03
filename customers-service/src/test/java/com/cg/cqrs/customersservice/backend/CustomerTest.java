package com.cg.cqrs.customersservice.backend;

import io.eventuate.Aggregates;
import io.eventuate.DefaultMissingApplyEventMethodStrategy;
import io.eventuate.Event;

import org.junit.Before;
import org.junit.Test;

import com.cg.cqrs.common.customer.CustomerCreatedEvent;
import com.cg.cqrs.common.customer.CustomerCreditLimitExceededEvent;
import com.cg.cqrs.common.customer.CustomerCreditReservedEvent;
import com.cg.cqrs.customersservice.backend.CreateCustomerCommand;
import com.cg.cqrs.customersservice.backend.Customer;
import com.cg.cqrs.customersservice.backend.CustomerCommand;
import com.cg.cqrs.customersservice.backend.ReserveCreditCommand;

import java.util.List;

import static io.eventuate.EventUtil.events;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

  private Customer customer;
  private List<Event> events;

  @Before
  public void createEmptyCustomer() {
    customer = new Customer();
  }

  @Test
  public void testCreate() {

    process(new CreateCustomerCommand(CustomerMother.name, CustomerMother.creditLimit));

    assertEventEquals(new CustomerCreatedEvent(CustomerMother.name, CustomerMother.creditLimit));

    applyEventsToMutableAggregate();

    assertEquals(CustomerMother.creditLimit, customer.getCreditLimit());
    assertEquals(CustomerMother.creditLimit, customer.availableCredit());
  }

  private void applyEventsToMutableAggregate() {
    Aggregates.applyEventsToMutableAggregate(customer, events, DefaultMissingApplyEventMethodStrategy.INSTANCE);
  }


  @Test
  public void testReserveCredit() {
    initializeCustomer();

    process(new ReserveCreditCommand(CustomerMother.orderTotalWithinCreditLimit, CustomerMother.orderId));

    assertEventEquals(new CustomerCreditReservedEvent(CustomerMother.orderId, CustomerMother.orderTotalWithinCreditLimit));

    applyEventsToMutableAggregate();

    assertEquals(CustomerMother.creditLimit, customer.getCreditLimit());
    assertEquals(CustomerMother.creditLimit.subtract(CustomerMother.orderTotalWithinCreditLimit), customer.availableCredit());

  }


  @Test
  public void testCreditLimitExceeded() {
    initializeCustomer();

    process(new ReserveCreditCommand(CustomerMother.orderTotalThatExceedsCreditLimit, CustomerMother.orderId));

    assertEventEquals(new CustomerCreditLimitExceededEvent(CustomerMother.orderId));

    applyEventsToMutableAggregate();

    assertEquals(CustomerMother.creditLimit, customer.getCreditLimit());
    assertEquals(CustomerMother.creditLimit, customer.availableCredit());
  }

  private <T extends CustomerCommand> void process(T command) {
    events = customer.processCommand(command);
  }


  private void assertEventEquals(Event expectedEvent) {
    assertEquals(events(expectedEvent), events);
  }

  private void initializeCustomer() {
    process(new CreateCustomerCommand(CustomerMother.name, CustomerMother.creditLimit));
    applyEventsToMutableAggregate();
  }

}