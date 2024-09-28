package com.springdatajpa.springboot.repository;


import com.springdatajpa.springboot.entity.Role;
import com.springdatajpa.springboot.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ManyToManyUnidirectionalTests {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void saveUserMethod(){

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setEmail("john.smith@gmail.com");
        user.setPassword("someSillyPassword");

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setName("ROLE_USER ");

        user.getRoles().add(adminRole);
        user.getRoles().add(userRole);

        userRepository.save(user);

    }

    @Test
    void updateUserMethod(){
        User user = userRepository.findById(1L).get();
        user.setFirstName("Jill");
        user.setEmail("jill.smith@gmail.com");

        Role role = new Role();
        role.setName("ROLE_QA");

        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Test
    void fetchUserMethod(){

        User user = userRepository.findById(1L).get();
        logger.info("User details -> {}", user);

        Set<Role> roles = user.getRoles();
        logger.info("Roles -> {}", roles);
        // each Role
        roles.forEach(role -> {
            logger.info("Role -> {}", role.getName());
        });
    }

    @Test
    void deleteUser(){
        userRepository.deleteById(1L);
    }

}
