package com.cg.cqrs.ordersservice.backend;

import com.cg.cqrs.common.domain.Money;

import io.eventuate.EntityWithIdAndVersion;

public interface OrderService {

    EntityWithIdAndVersion<Order> createOrder(String customerId, Money orderTotal);
}
