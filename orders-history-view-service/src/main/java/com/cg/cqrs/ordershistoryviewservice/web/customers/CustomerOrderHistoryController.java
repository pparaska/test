package com.cg.cqrs.ordershistoryviewservice.web.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cqrs.ordershistorycommon.CustomerView;
import com.cg.cqrs.ordershistoryviewservice.backend.CustomerViewRepository;

@RestController
public class CustomerOrderHistoryController {

  private final CustomerViewRepository customerViewRepository;

  @Autowired
  public CustomerOrderHistoryController(CustomerViewRepository customerViewRepository) {
    this.customerViewRepository = customerViewRepository;
  }

  @RequestMapping(value="/customers/{customerId}", method= RequestMethod.GET)
  public ResponseEntity<CustomerView> getCustomer(@PathVariable String customerId) {
    CustomerView customer = customerViewRepository.findOne(customerId);
    System.out.println("Found customer=" + customer + " for " + customerId);
    if (customer == null)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else
      return new ResponseEntity<>(customer, HttpStatus.OK);
  }


}
