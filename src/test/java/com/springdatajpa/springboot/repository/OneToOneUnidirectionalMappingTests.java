package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class OneToOneUnidirectionalMappingTests {

    @Autowired
    private OrderRepository orderRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");

        order.setBillingAddress(address);
        orderRepository.save(order);
    }

    @Test
    void getAllOrderMethod(){
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order -> {
            logger.info("order id -> {}", order.getId());
        });
    }

    @Test
    void getOrderMethod(){
        Order order = orderRepository.findById(2L).get();
        logger.info(order.toString());
    }

    @Test
    void updateOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        order.setStatus("DELIVERED");
        order.getBillingAddress().setZipCode("411087");
        orderRepository.save(order);
    }

    @Test
    void deleteOrderMethod(){
        // Order order = orderRepository.findById(1L).get();
        // orderRepository.delete(order);
        orderRepository.deleteById(1L);
    }
}
