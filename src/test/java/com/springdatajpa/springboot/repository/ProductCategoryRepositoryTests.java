package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import com.springdatajpa.springboot.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryRepositoryTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductCategoryRepositoryTests.class);

    @Test
    void saveProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("books");
        productCategory.setCategoryDescription("books description");

        Product product1 = new Product();
        product1.setName("Core Java");
        product1.setPrice(new BigDecimal(1000));
        product1.setImageUrl("image1.png");
        product1.setSku("AB");
        product1.setActive(true);

        // Establishing the relationship between both since its bidirectional mapping
        product1.setCategory(productCategory);
        productCategory.getProducts().add(product1);

        Product product2 = new Product();
        product2.setName("Effective Java");
        product2.setPrice(new BigDecimal(2000));
        product2.setImageUrl("image2.png");
        product2.setSku("ABC");
        product2.setActive(true);

        // Establishing the relationship between both since its bidirectional mapping
        product2.setCategory(productCategory);
        productCategory.getProducts().add(product2);

        // saving the details to the database
        ProductCategory savedProductCategory = productCategoryRepository.save(productCategory);
        logger.info("savedProductCategory -> {}", savedProductCategory);
        logger.info("savedProducts -> {}", savedProductCategory.getProducts());
    }

    @Test
    void fetchProductCategory(){
        ProductCategory category = productCategoryRepository.findById(1L).get();
        logger.info("category -> {}", category);
        logger.info(" first product details -> {}", category.getProducts().get(0).getName()); // This will fail since ProductCategory have LAZY type

    }

    @Test
    @Transactional
    void fetchProductCategoryOnDemand(){
        ProductCategory category = productCategoryRepository.findById(1L).get();
        logger.info("category -> {}", category); // 1st SQL query
        logger.info("fetching product details from product category ");
        logger.info(" first product details -> {}", category.getProducts()); // 2nd SQL query
        // Two separate SQL queries performed to get first product category details
        // and then 2nd SQL query to get the product details

    }

    @Test
    void fetchProductCategoryFromProduct(){
        Product products = productRepository.findById(1L).get();
        logger.info("products -> {}", products.getName());
        logger.info("product category details from product-> {}", products.getCategory()); // fetching is EAGER, Left joining happening
    }

}