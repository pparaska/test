package com.cg.cqrs.ordersservice.backend;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.List;

import com.cg.cqrs.common.order.OrderApprovedEvent;
import com.cg.cqrs.common.order.OrderCreatedEvent;
import com.cg.cqrs.common.order.OrderRejectedEvent;
import com.cg.cqrs.common.order.OrderState;

import static io.eventuate.EventUtil.events;

public class Order
        extends ReflectiveMutableCommandProcessingAggregate<Order, OrderCommand> {

  private OrderState state;
  private String customerId;

  public List<Event> process(CreateOrderCommand cmd) {
    return events(new OrderCreatedEvent(cmd.getCustomerId(), cmd.getOrderTotal()));
  }

  public void apply(OrderCreatedEvent event) {
    this.state = OrderState.CREATED;
    this.customerId = event.getCustomerId();
  }

  public OrderState getState() {
    return state;
  }

  public List<Event> process(ApproveOrderCommand cmd) {
    return events(new OrderApprovedEvent(customerId));
  }

  public List<Event> process(RejectOrderCommand cmd) {
    return events(new OrderRejectedEvent(customerId));
  }


  public void apply(OrderApprovedEvent event) {
    this.state = OrderState.APPROVED;
  }


  public void apply(OrderRejectedEvent event) {
    this.state = OrderState.REJECTED;
  }


}
