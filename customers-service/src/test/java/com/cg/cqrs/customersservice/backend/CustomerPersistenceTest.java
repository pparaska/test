package com.cg.cqrs.customersservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.sync.AggregateRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.customersservice.backend.CreateCustomerCommand;
import com.cg.cqrs.customersservice.backend.Customer;
import com.cg.cqrs.customersservice.backend.CustomerCommand;
import com.cg.cqrs.customersservice.backend.ReserveCreditCommand;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= CustomerServiceInProcessComponentTestConfiguration.class,
        webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class CustomerPersistenceTest {

  @Autowired
  private AggregateRepository<Customer, CustomerCommand> aggregateRepository;

  @Test
  public void shouldCreateAndUpdateCustomer() {
    EntityWithIdAndVersion<Customer> cwm = aggregateRepository.save(new CreateCustomerCommand("Fred", new Money(1234)));

    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-1"));
    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-2"));
    aggregateRepository.update(cwm.getEntityId(), new ReserveCreditCommand(new Money(11), "order-3"));

  }
}
