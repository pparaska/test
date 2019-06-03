package com.cg.cqrs.ordersservice.web;

import io.eventuate.javaclient.commonimpl.JSonMapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.cqrs.orderscommmon.CreateOrderRequest;
import com.cg.cqrs.ordersservice.backend.OrderService;
import com.cg.cqrs.ordersservice.web.OrderController;

import static com.cg.cqrs.ordersservice.web.OrderMother.applicationJson;
import static com.cg.cqrs.ordersservice.web.OrderMother.createOrderResult;
import static com.cg.cqrs.ordersservice.web.OrderMother.customerId;
import static com.cg.cqrs.ordersservice.web.OrderMother.orderId;
import static com.cg.cqrs.ordersservice.web.OrderMother.orderTotal;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

  private OrderService orderService;
  private OrderController orderController;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.orderService = mock(OrderService.class);
    this.orderController = new OrderController(orderService);
    this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
  }

  @Test
  public void shouldCreateOrder() throws Exception {
    CreateOrderRequest request = new CreateOrderRequest(customerId, orderTotal);
    when(orderService.createOrder(customerId, orderTotal)).thenReturn(createOrderResult);
    this.mockMvc.perform(post("/orders").content(JSonMapper.toJson(request))
            .contentType(applicationJson).accept(applicationJson))
            .andExpect(status().isOk())
            .andExpect(content().contentType(applicationJson))
            .andExpect(jsonPath("$.orderId").value(orderId));
    verify(orderService).createOrder(customerId, orderTotal);
    verifyNoMoreInteractions(orderService);
  }
}