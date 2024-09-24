package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);

    @Test
    public void saveProduct(){
        // create product
        Product product = new Product();
        product.setName("product-1");
        product.setDescription("product-1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        // save product
        Product savedObject = productRepository.save(product);

        // display product info
        logger.info("ID of the saved product -> {}",savedObject.getId());
        logger.info("All the details of the saved product -> {}",savedObject);

    }

    @Test
    public void updateProductUsingSave(){
        Long id=1L;
        // find the product
        Product product = productRepository.findById(id).get();
        // Update entity details
        product.setName("product-1 Updated");
        product.setDescription("product-1 description Updated");

        // saved to the db
        Product updatedObject =productRepository.save(product);

        logger.info("All the details of the updated product -> {}",updatedObject);
    }

    @Test
    public void findByIdMethod(){
        Long id=1L;
        Product product = productRepository.findById(id).get();
        logger.info("product name by id -> {}",product.getName());
    }

    @Test
    void saveAllMethod(){

        // create product
        Product product2 = new Product();
        product2.setName("product-2");
        product2.setDescription("product-2 description");
        product2.setSku("114ABCD");
        product2.setPrice(new BigDecimal(100));
        product2.setActive(true);
        product2.setImageUrl("product2.png");

        // create product
        Product product3 = new Product();
        product3.setName("product-3");
        product3.setDescription("product-3 description");
        product3.setSku("115ABCDE");
        product3.setPrice(new BigDecimal(200));
        product3.setActive(true);
        product3.setImageUrl("product3.png");

        productRepository.saveAll(List.of(product2, product3));
    }

    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();

        products.forEach( p -> System.out.println(p.getName()));
    }

    @Test
    void deleteByIdMethod(){
        productRepository.deleteById(1L);
    }

    @Test
    void deleteMethod(){
        // find the entity, because delete method expects entity
        Long id = 3L;
        Product product = productRepository.findById(id).get();

        // delete(entity)
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod(){
        // productRepository.deleteAll();

        // delete specefic multiple entriess , instead of all entries
        Product product = productRepository.findById(8L).get();
        Product product2 = productRepository.findById(9L).get();
        productRepository.deleteAll(List.of(product,product2));

    }

    @Test
    void countMethod(){
        long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
        Long id = 7L;
        boolean result = productRepository.existsById(id);
        logger.info("Entry exists or not -> {}", result);
    }

}