package com.company;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectTest {

    @Autowired
    UserServiceImpl userService;


    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void removeUserTest() {
        long id = 1234567890985L;
        userService.addUser(new User(id,"Kgaugelo","Ramaramela"));
        assertTrue(userService.removeUser(id));
    }

    @Test
    void getUserTest() {
        long id = 17695723657325735L;
        userService.addUser(new User(id,"Bataung","MaraT"));
        String name = "Bataung";
        String surname = "MaraT";
        assertEquals(new User(id, name, surname).getId(), userService.getUser(id).getId());
    }

    @Test
    void getCachedUserTest() {
        long id = 176957236573257735L;
        userService.addUser(new User(id, "Tshidiso", "Khoza"));
        userService.getUser(id);
        userService.getUser(id);
        userService.getUser(id);
        userService.getUser(id);

    }

    @Test
    void securityTest() {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth("SageKay", "Password#10").getForEntity("/user", String.class);


    }
}
