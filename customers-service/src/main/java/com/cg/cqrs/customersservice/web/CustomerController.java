package com.cg.cqrs.customersservice.web;

import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cqrs.customerscommon.CreateCustomerRequest;
import com.cg.cqrs.customerscommon.CreateCustomerResponse;
import com.cg.cqrs.customerscommon.GetCustomerResponse;
import com.cg.cqrs.customersservice.backend.Customer;
import com.cg.cqrs.customersservice.backend.CustomerService;

@RestController
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
    EntityWithIdAndVersion<Customer> ewidv = customerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCreditLimit());
    return new CreateCustomerResponse(ewidv.getEntityId());
  }

  @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
  public ResponseEntity<GetCustomerResponse> GetCustomer(@PathVariable String customerId) {
    EntityWithMetadata<Customer> customerWithMetadata;
    try {
      customerWithMetadata = customerService.findById(customerId);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Customer customer = customerWithMetadata.getEntity();
    GetCustomerResponse response =
            new GetCustomerResponse(customerWithMetadata.getEntityIdAndVersion().getEntityId(), customer.getCreditLimit(),
                    customer.availableCredit());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
