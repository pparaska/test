package com.cg.cqrs.ordersservice.backend;

import io.eventuate.sync.AggregateRepository;

import com.cg.cqrs.common.domain.Money;

import io.eventuate.EntityWithIdAndVersion;

public class OrderServiceImpl implements OrderService {

  private final AggregateRepository<Order, OrderCommand> orderRepository;
  private CustomerService customerService;

  public OrderServiceImpl(AggregateRepository<Order, OrderCommand> orderRepository, CustomerService customerService) {
    this.orderRepository = orderRepository;
    this.customerService = customerService;
  }

  @Override
  public EntityWithIdAndVersion<Order>
        createOrder(String customerId, Money orderTotal) {
    customerService.verifyCustomerCustomerId(customerId);
    return orderRepository.save(new CreateOrderCommand(customerId, orderTotal));
  }
}
