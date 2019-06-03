package com.cg.cqrs.common.order;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.cg.cqrs.ordersservice.backend.Order")
public interface OrderEvent extends Event {
}
