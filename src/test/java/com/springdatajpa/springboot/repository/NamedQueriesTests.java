package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
/*
*If we want to create a JPQL query, we have to annotate our entity with the @NamedQuery annotation.
*If we want to create a SQL query, we have to annotate our entity with the @NamedNativeQuery annotation.
*
* Use @NamedQuery annotation’s name attribute to set name of the named query
* Use @NamedQuery annotation’s query attribute to set the JPQL query
*
* Set the name of the named query as the value of the @NamedNativeQuery annotation’s name attribute.
* Set the SQL query as the value of the @NamedNativeQuery annotation’s query attribute.
* Set the returned entity class (Product.class) as the value of the of the @NamedNativeQuery annotation’s resultClass attribute.
*/
@SpringBootTest
public class NamedQueriesTests {

    @Autowired
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Named JPQL Tests
    @Test
    void findByPriceNamedMethod(){
        Product product = productRepository.findByPrice(new BigDecimal(200));
        logger.info("product id-> {}", product.getId());
        logger.info("product name-> {}", product.getName());
    }

    @Test
    void findAllOrderByNameDescMethod(){
        List<Product> products = productRepository.findAllOrderByNameDesc();
        products.forEach(product -> {
            logger.info("product id-> {} & name-> {}", product.getId(), product.getName());
        });
    }

    // Named Native Queries tests
    @Test
    void findByDescriptionNamedNativeMethod(){
        Product product = productRepository.findByDescription("product-1 description");
        logger.info("product id-> {} & name-> {}", product.getId(), product.getName());
    }

    // NamedNative NamedParam Query
    @Test
    void findByDescriptionNamedParam(){
        Product product = productRepository.findByDescriptionNamedParam("product-2 description");
        logger.info("product id-> {} & name-> {}", product.getId(), product.getName());
    }

    @Test
    void findAllOrderByNameASCMethod(){
        List<Product> products = productRepository.findAllOrderByNameASC();
        products.forEach(product -> {
            logger.info("product id-> {} & name-> {}", product.getId(), product.getName());
        });
    }

}
