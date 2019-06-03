package com.cg.cqrs.views.orderhistory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.ordershistorycommon.CustomerView;
import com.cg.cqrs.ordershistoryviewservice.backend.CustomerViewRepository;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderHistoryViewServiceTestConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerViewRepositoryIntegrationTest {

  @Autowired
  private CustomerViewRepository customerViewRepository;

  @Test
  public void shouldCreateAndFindCustomer() {

    String customerId = UUID.randomUUID().toString();
    Money creditLimit = new Money(2000);
    String customerName = "Fred";

    customerViewRepository.addCustomer(customerId, customerName, creditLimit);
    CustomerView customerView = customerViewRepository.findOne(customerId);

    assertEquals(customerId, customerView.getId());
    assertEquals(customerName, customerView.getName());
    assertEquals(0, customerView.getOrders().size());
    assertEquals(creditLimit, customerView.getCreditLimit());
  }
}
