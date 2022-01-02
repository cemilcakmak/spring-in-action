package com.spring.tacocloud.repository;

import com.spring.tacocloud.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Order save(Order order);
}
