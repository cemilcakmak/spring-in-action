package com.spring.tacocloud.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.tacocloud.domain.Order;
import com.spring.tacocloud.domain.Taco;
import com.spring.tacocloud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.*;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setCreatedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();

        for(Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }

    @Override
    public List<Order> findByDeliveryZip(String deliveryZip) {
        return null;
    }

    @Override
    public List<Order> readOrdersByDeliveryZipAndPlaceAtBetween(String deliveryZip, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Order> readOrdersDeliveredInSeattle() {
        return null;
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("createdAt", order.getCreatedAt());
        long orderId = orderInserter.executeAndReturnKey(values).longValue();

        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }

    @Override
    public <S extends Order> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Order> findAll() {
        return null;
    }

    @Override
    public Iterable<Order> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Order entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
