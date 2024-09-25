package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPQLQueriesTests {

    @Autowired
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void findByNameOrDescriptionJPQLMethod(){
        Product product = productRepository.findByNameOrDescriptionJPQL("product-1", "product-1 description");
        logger.info("findByNameOrDescriptionJPQLMethod -> {}", product);
    }

    @Test
    void findByNameOrDescriptionJPQLNamedParamMethod(){
        List<Product> products = productRepository.findByNameOrDescriptionJPQLNamedParam("product-2","product-1 description");
        products.forEach(product -> {
            logger.info("findByNameOrDescriptionJPQLNamedParamMethod -> {}", product);
        });
    }

    @Test
    void findByNameOrDescriptionIndexNativeMethod(){
        List<Product> products = productRepository.findByNameOrDescriptionIndexNative("product-2","product-1 description");
        logger.info("findByNameOrDescriptionIndexNativeMethod");
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
        }));
    }

    @Test
    void findByNameOrDescriptionNamedParamNativeMethod(){
        List<Product> products = productRepository.findByNameOrDescriptionNamedParamNative("product-2","product-1 description");
        logger.info("findByNameOrDescriptionNamedParamNativeMethod");
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
        }));
    }
}
