package com.cg.cqrs.ordershistoryviewservice.backend;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.common.order.OrderState;

public interface OrderViewRepositoryCustom {
  void addOrder(String orderId, Money orderTotal);
  void updateOrderState(String orderId, OrderState state);
}
