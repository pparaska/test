package com.cg.cqrs.customersservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cg.cqrs.commonswagger.CommonSwaggerConfiguration;
import com.cg.cqrs.customersservice.web.CustomerWebConfiguration;

@Configuration
@Import({CustomerWebConfiguration.class,
        EventuateDriverConfiguration.class,
        CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class CustomersServiceMain {
  public static void main(String[] args) {
    SpringApplication.run(CustomersServiceMain.class, args);
  }
}
