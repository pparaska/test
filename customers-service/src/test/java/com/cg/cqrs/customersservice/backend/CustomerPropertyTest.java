package com.cg.cqrs.customersservice.backend;

import com.cg.cqrs.common.customer.CustomerCreatedEvent;
import com.cg.cqrs.common.customer.CustomerCreditLimitExceededEvent;
import com.cg.cqrs.common.customer.CustomerCreditReservedEvent;
import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.customersservice.backend.Customer;
import com.cg.cqrs.customersservice.backend.ReserveCreditCommand;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.eventuate.Aggregates;
import io.eventuate.DefaultMissingApplyEventMethodStrategy;
import io.eventuate.Event;

import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class CustomerPropertyTest {

  @Property
  public void reserveCredit(@InRange(minInt = 1) int orderTotal) {
    Customer customer = Aggregates.recreateAggregate(Customer.class,
            singletonList(new CustomerCreatedEvent(CustomerMother.name, CustomerMother.creditLimit)), DefaultMissingApplyEventMethodStrategy.INSTANCE);

    List<Event> events = customer.process(new ReserveCreditCommand(new Money(orderTotal), CustomerMother.orderId));

    assertEquals(1, events.size());
    Class<? extends Event> eventClass = events.get(0).getClass();

    if (CustomerMother.creditLimit.isGreaterThanOrEqual(new Money(orderTotal))) {
      assertEquals(CustomerCreditReservedEvent.class, eventClass);
    } else
      assertEquals(CustomerCreditLimitExceededEvent.class, eventClass);

  }
}
