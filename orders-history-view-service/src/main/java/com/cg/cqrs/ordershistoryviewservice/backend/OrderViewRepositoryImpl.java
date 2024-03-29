package com.cg.cqrs.ordershistoryviewservice.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cg.cqrs.common.domain.Money;
import com.cg.cqrs.common.order.OrderState;
import com.cg.cqrs.ordershistorycommon.OrderView;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class OrderViewRepositoryImpl implements OrderViewRepositoryCustom {

  private MongoTemplate mongoTemplate;

  @Autowired
  public OrderViewRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void addOrder(String orderId, Money orderTotal) {
    mongoTemplate.upsert(new Query(where("id").is(orderId)),
            new Update().set("state", OrderState.CREATED).set("orderTotal", orderTotal), OrderView.class);
  }

  @Override
  public void updateOrderState(String orderId, OrderState state) {
    mongoTemplate.updateFirst(new Query(where("id").is(orderId)),
            new Update().set("state", state), OrderView.class);
  }
}
