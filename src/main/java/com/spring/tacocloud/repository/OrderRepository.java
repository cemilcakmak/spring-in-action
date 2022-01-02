package com.spring.tacocloud.repository;

import com.spring.tacocloud.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Order save(Order order);

    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> readOrdersByDeliveryZipAndPlaceAtBetween(String deliveryZip,  Date startDate, Date endDate);

    @Query("Order o WHERE o.deliverCity = 'Seattle'")
    List<Order> readOrdersDeliveredInSeattle();
}
