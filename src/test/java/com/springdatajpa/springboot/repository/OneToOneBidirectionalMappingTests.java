package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OneToOneBidirectionalMappingTests {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void saveAddressMethod(){
        Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");

        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        order.setBillingAddress(address);
        address.setOrder(order);

        addressRepository.save(address); // Owner side

    }

    @Test
    void fetchAddressMethod(){
        Address address = addressRepository.findById(1L).get(); // This will fetch both addresses and orders details.
    }

    @Test
    void updateAddressMethod(){
        Address address = addressRepository.findById(1L).get();
        address.setZipCode("411048");
        address.getOrder().setStatus("DELIVERED");
        addressRepository.save(address);
    }

    @Test
    void deleteAddressMethod(){
        addressRepository.deleteById(1L);
    }

    // Fetching results from orders to Address and vice versa.

    @Test
    void fetchOrdersAddress(){
        Order order= orderRepository.findById(1L).get();
        String city = order.getBillingAddress().getCity();
        assertEquals("Pune",city);
    }

    @Test
    void fetchAddressStatus(){
        Address address = addressRepository.findById(1L).get();
        String status= address.getOrder().getStatus();
        assertEquals("IN PROGRESS",status);
    }

}
