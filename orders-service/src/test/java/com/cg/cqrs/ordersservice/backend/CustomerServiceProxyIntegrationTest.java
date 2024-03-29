package com.cg.cqrs.ordersservice.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.customerscommon.CreateCustomerRequest;
import com.cg.cqrs.customerscommon.CreateCustomerResponse;
import com.cg.cqrs.ordersservice.backend.CustomerNotFoundException;
import com.cg.cqrs.ordersservice.backend.CustomerServiceProxy;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CustomerServiceProxyIntegrationTestConfiguration.class,
        webEnvironment= SpringBootTest.WebEnvironment.NONE,
        properties={"customer.service.url=http://${DOCKER_HOST_IP}:8081/customers/{customerId}"}
)
@DirtiesContext
public class CustomerServiceProxyIntegrationTest {

  @Autowired
  private CustomerServiceProxy customerServiceProxy;

  @Autowired
  private RestTemplate restTemplate;

  @Value("http://${DOCKER_HOST_IP}:8081/customers")
  private String customerServiceRootUrl;

  @Test
  public void shouldVerifyExistingCustomer() {
    ResponseEntity<CreateCustomerResponse> response = restTemplate.postForEntity(customerServiceRootUrl,
            new CreateCustomerRequest("Fred", new Money(123)), CreateCustomerResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    customerServiceProxy.verifyCustomerCustomerId(response.getBody().getCustomerId());
  }

  @Test(expected = CustomerNotFoundException.class)
  public void shouldRejectNonExistentCustomer() {
    customerServiceProxy.verifyCustomerCustomerId("1223232-none");
  }

}
