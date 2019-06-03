package com.cg.cqrs.ordershistoryviewservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cg.cqrs.commonswagger.CommonSwaggerConfiguration;
import com.cg.cqrs.ordershistoryviewservice.web.OrderHistoryViewWebConfiguration;

@Configuration
@Import({OrderHistoryViewWebConfiguration.class, EventuateDriverConfiguration.class, CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class OrderHistoryQuerySideService {

  public static void main(String[] args) {
    SpringApplication.run(OrderHistoryQuerySideService.class, args);
  }
}
