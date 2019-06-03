package com.cg.cqrs.customersservice.backend;

import com.cg.cqrs.common.domain.Money;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;

public interface CustomerService {

  EntityWithIdAndVersion<Customer> createCustomer(String name, Money creditLimit);

  EntityWithMetadata<Customer> findById(String customerId);
}
