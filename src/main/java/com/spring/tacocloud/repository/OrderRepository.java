package com.spring.tacocloud.repository;

import com.spring.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
