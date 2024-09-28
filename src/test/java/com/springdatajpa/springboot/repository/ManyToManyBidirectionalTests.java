package com.springdatajpa.springboot.repository;


import com.springdatajpa.springboot.entity.Role;
import com.springdatajpa.springboot.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class ManyToManyBidirectionalTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void saveRoleMethod(){

        User user1 = new User();
        user1.setFirstName("Rick");
        user1.setLastName("Sanchez");
        user1.setEmail("rick.sanchez@gmail.com");
        user1.setPassword("someSillyPassword");

        User user2 = new User();
        user2.setFirstName("Morty");
        user2.setLastName("Smith");
        user2.setEmail("morty.smith@gmail.com");
        user2.setPassword("someSillyPassword");

        Role devRole = new Role();
        devRole.setName("ROLE_DEVELOPER");

        // since now its a bidirectional mapping, set the relationship with each other
        devRole.getUsers().add(user1);
        devRole.getUsers().add(user2);

        user1.getRoles().add(devRole);
        user2.getRoles().add(devRole);

        roleRepository.save(devRole);
    }

    @Test
    void fetchRoles(){
        Role role = roleRepository.findById(7L).get();
        logger.info(role.getName());
        logger.info(role.getUsers().toString());
        // fetching users from roles
        // fetching is  EAGER, left joining already happening
        role.getUsers().forEach(user ->{
            logger.info(user.getFirstName());
        });

    }

    @Test
    void fetchAllRoles(){
        List<Role> roles = roleRepository.findAll();
        roles.forEach((role) -> {
            logger.info(role.getName());
            role.getUsers().forEach(user -> {
                logger.info(user.getFirstName());
            });
        });
    }

}
