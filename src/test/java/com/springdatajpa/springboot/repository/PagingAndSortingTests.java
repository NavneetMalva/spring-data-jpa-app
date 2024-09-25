package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PagingAndSortingTests {

    @Autowired
    private ProductRepository  productRepository;

    private final Logger logger = LoggerFactory.getLogger(PagingAndSortingTests.class);

    @Test
    void paginationMethod(){
        int pageNo = 0;
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // findAll method and pass the pageable instance
        Page<Product> page=productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(product -> {
            logger.info("product id-> {} & name-> {}", product.getId(), product.getName());
        });

        // total pages
        int totalPage = page.getTotalPages();
        // total elements
        long totalElements = page.getTotalElements();
        // number of elements
        int numberOfElements = page.getNumberOfElements();
        // size
        int size = page.getSize();
        // last
        boolean isLast = page.isLast();
        // first
        boolean isFirst = page.isFirst();

        System.out.println("total page -> " + totalPage);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println(" size ->" + size);
        System.out.println(" isLast -> " + isLast);
        System.out.println(" isFirst -> " + isFirst);

    }

    @Test
    void sortingMethod(){
        /*
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        products.forEach(product -> {
            logger.info("product id-> {} & name-> {} & price-> {}", product.getId(), product.getName(),product.getPrice());
        });
        */


        String sortBy = "price";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        List<Product> products =productRepository.findAll(sort);
        products.forEach(product -> {
            logger.info("product id-> {} & name-> {} & price-> {}", product.getId(), product.getName(),product.getPrice());
        });

    }

    @Test
    void sortingByMultipleFields(){
        String sortBy = "name";
        String sortByDesc="description";
        String sortDir = "desc";

        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                            Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Sort sortByDescription = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortByDesc).ascending(): Sort.by(sortByDesc).descending();

        Sort sortByGroup = sortByName.and(sortByDescription);
        List<Product> products =productRepository.findAll(sortByGroup);
        products.forEach(product -> {
            logger.info("product id-> {} & name-> {} & price-> {}", product.getId(), product.getName(),product.getPrice());
        });
    }

    @Test
    void pagingAndSortingTogetherMethod(){
        int pageNo = 1;
        int pageSize = 4;
        String sortBy="price";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> products = page.getContent();
        products.forEach(product -> {
            logger.info("product id-> {} & name-> {} & price-> {}", product.getId(), product.getName(),product.getPrice());
        });

        // total pages
        int totalPage = page.getTotalPages();
        // total elements
        long totalElements = page.getTotalElements();
        // number of elements
        int numberOfElements = page.getNumberOfElements();
        // size
        int size = page.getSize();
        // last
        boolean isLast = page.isLast();
        // first
        boolean isFirst = page.isFirst();
        System.out.println("total page -> " + totalPage);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println(" size ->" + size);
        System.out.println(" isLast -> " + isLast);
        System.out.println(" isFirst -> " + isFirst);
    }

}

