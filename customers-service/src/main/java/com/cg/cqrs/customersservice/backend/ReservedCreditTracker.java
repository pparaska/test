package com.cg.cqrs.customersservice.backend;

import java.util.HashMap;
import java.util.Map;

import com.cg.cqrs.common.domain.Money;

public class ReservedCreditTracker {
  private Map<String, Money> creditReservations = new HashMap<>();

  Money reservedCredit() {
    return creditReservations.values().stream().reduce(Money.ZERO, Money::add);
  }

  void addReservation(String orderId, Money orderTotal) {
    creditReservations.put(orderId, orderTotal);
  }
}