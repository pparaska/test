package com.cg.cqrs.ordershistoryviewservice.backend;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.common.order.OrderState;

public interface CustomerViewRepositoryCustom {

  void addCustomer(String customerId, String customerName, Money creditLimit);

  void addOrder(String customerId, String orderId, Money orderTotal);

  void updateOrderState(String customerId, String orderId, OrderState state);
}
