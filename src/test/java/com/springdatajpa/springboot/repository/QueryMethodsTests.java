package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
Rules to create query methods
1. The name of our query method must start with one of the following prefixes:
    find…By, read…By, query…By, count…By, and get…By.
    Examples: findByName, readByName, queryByName, getByName
2. If we want to limit the number of returned query results, we can add the First or the Top
    keyword before the first By word.
    Examples: findFirstByName, readFirst2ByName, findTop10ByName
3. If we want to select unique results, we have to add the Distinct keyword before the first By word.
    Examples: findDistinctByName or findNameDistinctBy
4. Combine property expression with AND and OR.
    Examples: findByNameOrDescription, findByNameAndDescription
*/

@SpringBootTest
public class QueryMethodsTests {

    @Autowired
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);

    @Test
    void findByNameMethod(){
        String name = "product-1";
        Product product = productRepository.findByName(name);
        logger.info("findByName method output -> {}, {}, {}",product.getId(), product.getName(),product.getDescription());
    }

    @Test
    void FindByIdMethod(){
        Optional<Product> product = productRepository.findById(10L);
        logger.info("FindByIdMethod Result -> {}", product);
    }

    @Test
    void findByNameOrDescriptionMethod(){
        List<Product> products = productRepository.findByNameOrDescription("product-1", "product description");
        products.forEach((product) -> {
            logger.info("-> {}", product.getId());
            logger.info("-> {}", product.getName());
        });
    }

    @Test
    void findByNameAndDescriptionMethod(){
        List<Product> products = productRepository.findByNameAndDescription("product-1", "product description");
        products.forEach((product) -> {
            logger.info("-> {}", product.getId());
            logger.info("-> {}", product.getName());
        });
    }

    @Test
    void findDistinctByNameMethod(){
        Product product = productRepository.findDistinctByName("product-1");
        logger.info("product id-> {}", product.getId());
        logger.info("product name-> {}", product.getName());
    }

    @Test
    void findByPriceGreaterThanMethod(){
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(150));
        products.forEach(product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
            logger.info("product price-> {}", product.getPrice());
        });
    }

    @Test
    void findByPriceLessThanMethod(){
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(150));
        products.forEach(product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
            logger.info("product price-> {}", product.getPrice());
        });
    }

    @Test
    void findByNameContainingMethod(){
        List<Product> products = productRepository.findByNameContaining("product");
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
            logger.info("product price-> {}", product.getPrice());
        }));
    }

    @Test
    void findByNameLikeMethod(){
        List<Product> products = productRepository.findByNameLike("product");
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
            logger.info("product price-> {}", product.getPrice());
        }));
    }

    @Test
    void findByPriceBetweenMethod(){
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(50), new BigDecimal(150));
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
            logger.info("product price-> {}", product.getPrice());
        }));
    }

    @Test
    void findByDateCreatedBetweenMethod(){
        // start date
        LocalDateTime startDate = LocalDateTime.of(2022,02,13,17,48,33);
        // end date
        LocalDateTime endDate = LocalDateTime.of(2022,02,13,18,15,21);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
        }));
    }

    @Test
    void findByNameInMethod(){

        List<Product> products = productRepository.findByNameIn(List.of("product-1", "product-2", "product-3"));
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
        }));
    }

    @Test
    void findFirst2ByOrderByNameAscMethod(){
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
        }));
    }

    @Test
    void findFirst2ByOrderByPriceDescMethod(){
        List<Product> products = productRepository.findFirst2ByOrderByPriceDesc();
        products.forEach((product -> {
            logger.info("product id-> {}", product.getId());
            logger.info("product name-> {}", product.getName());
            logger.info("product price-> {}", product.getPrice());
        }));
    }

}
