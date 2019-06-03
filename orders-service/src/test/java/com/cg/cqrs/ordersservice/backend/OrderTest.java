package com.cg.cqrs.ordersservice.backend;


import io.eventuate.Event;
import io.eventuate.EventUtil;

import org.junit.Test;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.common.order.OrderApprovedEvent;
import com.cg.cqrs.common.order.OrderCreatedEvent;
import com.cg.cqrs.common.order.OrderRejectedEvent;
import com.cg.cqrs.common.order.OrderState;
import com.cg.cqrs.ordersservice.backend.ApproveOrderCommand;
import com.cg.cqrs.ordersservice.backend.CreateOrderCommand;
import com.cg.cqrs.ordersservice.backend.Order;
import com.cg.cqrs.ordersservice.backend.RejectOrderCommand;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderTest {

  Money orderTotal = new Money(10);
  String customerId = "customerID";

  @Test
  public void testCreate() {
    Order order = new Order();

    List<Event> events = order.process(new CreateOrderCommand(customerId, orderTotal));

    assertEquals(EventUtil.events(new OrderCreatedEvent(customerId, orderTotal)), events);

    order.apply((OrderCreatedEvent) events.get(0));

    assertEquals(OrderState.CREATED, order.getState());

  }

  @Test
  public void testApprove() {
    Order order = new Order();

    order.apply((OrderCreatedEvent) order.process(new CreateOrderCommand(customerId, orderTotal)).get(0));

    List<Event> events = order.process(new ApproveOrderCommand());

    assertEquals(EventUtil.events(new OrderApprovedEvent(customerId)), events);

    order.apply((OrderApprovedEvent) events.get(0));

    assertEquals(OrderState.APPROVED, order.getState());
  }

  @Test
  public void testReject() {
    Order order = new Order();

    order.apply((OrderCreatedEvent) order.process(new CreateOrderCommand(customerId, orderTotal)).get(0));

    List<Event> events = order.process(new RejectOrderCommand());

    assertEquals(EventUtil.events(new OrderRejectedEvent(customerId)), events);

    order.apply((OrderRejectedEvent) events.get(0));

    assertEquals(OrderState.REJECTED, order.getState());

  }
}