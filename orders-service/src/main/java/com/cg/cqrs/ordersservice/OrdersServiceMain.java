package com.cg.cqrs.ordersservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cg.cqrs.commonswagger.CommonSwaggerConfiguration;
import com.cg.cqrs.ordersservice.web.OrderWebConfiguration;

@Configuration
@Import({OrderWebConfiguration.class, EventuateDriverConfiguration.class, CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class OrdersServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(OrdersServiceMain.class, args);
  }
}
