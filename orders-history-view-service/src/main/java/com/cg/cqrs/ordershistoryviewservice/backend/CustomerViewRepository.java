package com.cg.cqrs.ordershistoryviewservice.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.cqrs.ordershistorycommon.CustomerView;

public interface CustomerViewRepository
        extends MongoRepository<CustomerView, String>, CustomerViewRepositoryCustom {
}
