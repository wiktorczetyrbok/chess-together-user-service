package com.myApp.web;

import com.myApp.web.dto.RegistrationDto;
import com.myApp.web.model.UserEntity;
import com.myApp.web.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegistrationSuccess() throws Exception {
        RegistrationDto user = new RegistrationDto();
        user.setEmail("testuser@test.com");
        user.setUsername("testuser");
        user.setPassword("testpassword");

        ResponseEntity<String> response = restTemplate.postForEntity("/register/save", user, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        UserEntity savedUser = userRepository.findByUsername("testuser");
       // assertNotNull(savedUser);
        assertEquals("testuser@test.com", savedUser.getEmail());
        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    public void testRegistrationDuplicateEmail() throws Exception {
        UserEntity existingUser = new UserEntity();
        existingUser.setEmail("testuser@test.com");
        existingUser.setUsername("testuser");
        existingUser.setPassword("testpassword");
        userRepository.save(existingUser);

        RegistrationDto user = new RegistrationDto();
        user.setEmail("testuser@test.com");
        user.setUsername("testuser2");
        user.setPassword("testpassword2");

        ResponseEntity<String> response = restTemplate.postForEntity("/register/save", user, String.class);
        System.out.println(response);
        assertTrue(response.getHeaders().getLocation().toString().endsWith("/register?fail"));


    }

    @Test
    public void testRegistrationDuplicateUsername() throws Exception {
        UserEntity existingUser = new UserEntity();
        existingUser.setEmail("testuser@test.com");
        existingUser.setUsername("testuser");
        existingUser.setPassword("testpassword");
        userRepository.save(existingUser);

        RegistrationDto user = new RegistrationDto();
        user.setEmail("testuser2@test.com");
        user.setUsername("testuser");
        user.setPassword("testpassword2");

        ResponseEntity<String> response = restTemplate.postForEntity("/register/save", user, String.class);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().endsWith("/register?fail"));

        UserEntity savedUser = userRepository.findByEmail("testuser2@test.com");
        assertNull(savedUser);
    }
}