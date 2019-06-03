package com.cg.cqrs.ordersservice.backend;


import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import java.util.concurrent.CompletableFuture;

import com.cg.cqrs.common.customer.CustomerCreditLimitExceededEvent;
import com.cg.cqrs.common.customer.CustomerCreditReservedEvent;

@EventSubscriber(id="orderWorkflow")
public class OrderWorkflow {

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<Order>>
        creditLimitReserved(EventHandlerContext<CustomerCreditReservedEvent> ctx) {
    String orderId = ctx.getEvent().getOrderId();

    return ctx.update(Order.class, orderId, new ApproveOrderCommand());
  }

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<Order>>
        creditLimitExceeded(EventHandlerContext<CustomerCreditLimitExceededEvent> ctx) {
    String orderId = ctx.getEvent().getOrderId();

    return ctx.update(Order.class, orderId, new RejectOrderCommand());
  }

}
