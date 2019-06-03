package com.cg.cqrs.ordersservice.web;

import io.eventuate.EntityIdAndVersion;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.Int128;

import org.springframework.http.MediaType;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.ordersservice.backend.Order;

public class OrderMother {
  static MediaType applicationJson = MediaType.parseMediaType("application/json;charset=UTF-8");
  static String customerId = "customer_id";
  static Money orderTotal = new Money(1234);
  static String orderId = "entity-id";
  static EntityWithIdAndVersion<Order> createOrderResult =
          new EntityWithIdAndVersion<>(
                  new EntityIdAndVersion(orderId, new Int128(1, 3)),
                  new Order());
}
