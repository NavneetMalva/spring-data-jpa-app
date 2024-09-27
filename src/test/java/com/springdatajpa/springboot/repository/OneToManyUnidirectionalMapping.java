package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyUnidirectionalMapping {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // save order along with also save it's order items
    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("199ABC");
        order.setStatus("In progress");

        // create order item 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
        orderItem1.setImageUrl("image1.png");
        order.getOrderItems().add(orderItem1);

        // create order item 2
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(3)));
        orderItem2.setImageUrl("image2.png");
        order.getOrderItems().add(orderItem2);

        order.setTotalPrice(order.getTotalAmount());
        order.setTotalQuantity(2);

        Address address = new Address();
        address.setCity("Mumbai");
        address.setStreet("River Side");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411098");

        // address became the owner side in address and order relationship
        order.setBillingAddress(address);
        address.setOrder(order);

        orderRepository.save(order);

    }

    @Test
    void fetchOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        logger.info("Getting all the Order details -> {}",order);
        /*
        Since the fetching is EAGER, we will be able to get the orderItem details too.
         */
        logger.info("Getting all the Order Items details -> {}",order.getOrderItems());

        for(OrderItem orderItem : order.getOrderItems()){
            logger.info("OrderItem -> {}", orderItem.getProduct().getName());
        }
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }

}
