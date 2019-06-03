package com.cg.cqrs.ordershistoryviewservice.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.cqrs.ordershistorycommon.OrderView;

public interface OrderViewRepository extends MongoRepository<OrderView, String>, OrderViewRepositoryCustom {
}
