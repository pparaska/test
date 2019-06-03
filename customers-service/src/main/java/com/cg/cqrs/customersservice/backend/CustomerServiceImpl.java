package com.cg.cqrs.customersservice.backend;

import io.eventuate.sync.AggregateRepository;

import com.cg.cqrs.common.domain.Money;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;

public class CustomerServiceImpl implements CustomerService {

  private final AggregateRepository<Customer, CustomerCommand> customerRepository;

  public CustomerServiceImpl(AggregateRepository<Customer, CustomerCommand> customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public EntityWithIdAndVersion<Customer> createCustomer(String name, Money creditLimit) {
    return customerRepository.save(new CreateCustomerCommand(name, creditLimit));
  }

  @Override
  public EntityWithMetadata<Customer> findById(String customerId) {
    return customerRepository.find(customerId);
  }
}
