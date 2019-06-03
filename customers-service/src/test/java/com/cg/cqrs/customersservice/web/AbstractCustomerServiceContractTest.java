package com.cg.cqrs.customersservice.web;

import com.cg.cqrs.common.customer.CustomerCreatedEvent;
import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.customersservice.backend.Customer;
import com.cg.cqrs.customersservice.backend.CustomerService;
import com.cg.cqrs.customersservice.web.CustomerController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import io.eventuate.Aggregates;
import io.eventuate.DefaultMissingApplyEventMethodStrategy;
import io.eventuate.EntityIdAndVersion;
import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithMetadata;
import io.eventuate.Int128;

import org.junit.Before;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractCustomerServiceContractTest {

  private CustomerService customerService;

  @Before
  public void setup() {
    customerService = mock(CustomerService.class);
    RestAssuredMockMvc.standaloneSetup(new CustomerController(customerService));
    when(customerService.findById("1223232-none")).thenThrow(new EntityNotFoundException());

    Customer customer = Aggregates.recreateAggregate(Customer.class,
            Collections.singletonList(new CustomerCreatedEvent("Fred", new Money(4566))), DefaultMissingApplyEventMethodStrategy.INSTANCE);

    EntityWithMetadata<Customer> result =
            new EntityWithMetadata<>(new EntityIdAndVersion("1223232", new Int128(1, 2)), Optional.empty(),
            Collections.emptyList(), customer);
    when(customerService.findById("1223232"))
            .thenReturn(result);
  }
}
