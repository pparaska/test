package com.cg.cqrs.common.customer;


import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.cg.cqrs.customersservice.backend.Customer")
public interface CustomerEvent extends Event {
}
