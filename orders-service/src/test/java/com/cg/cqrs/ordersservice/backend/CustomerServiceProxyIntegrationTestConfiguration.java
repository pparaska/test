package com.cg.cqrs.ordersservice.backend;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cg.cqrs.ordersservice.backend.CustomerServiceProxyConfiguration;

@Configuration
@Import(CustomerServiceProxyConfiguration.class)
@EnableAutoConfiguration
public class CustomerServiceProxyIntegrationTestConfiguration {
}
