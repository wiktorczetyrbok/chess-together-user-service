package com.myApp.web.controller;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.mapper.ClubMapper;
import com.myApp.web.model.Club;
import com.myApp.web.model.UserEntity;
import com.myApp.web.repository.ClubRepository;
import com.myApp.web.repository.UserRepository;
import com.myApp.web.service.ClubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClubControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "test", roles = "USER") //to fix
    public void createClubTest() {
        UserEntity testUser = userRepository.findByUsername("test");

        Club club = new Club();
        club.setTitle("Test Club");
        club.setPhotoUrl("https://example.com/test.jpg");
        club.setContent("Test content");
        club.setCity("Test city");
        club.setCreatedBy(testUser);
        ClubDto clubDto = ClubMapper.mapToClubDto(club);

        ResponseEntity<Void> response = restTemplate.postForEntity("/clubs", clubDto, Void.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().endsWith("/clubs"));

        Optional<Club> clubs = clubRepository.findByTitle("Test Club");
        assertEquals(1, clubs.stream());
        Club createdClub = clubs.get();

        assertEquals("Test Club", createdClub.getTitle());
        assertEquals("https://example.com/test.jpg", createdClub.getPhotoUrl());
        assertEquals("Test content", createdClub.getContent());
        assertEquals("Test city", createdClub.getCity());
    }

    @Test
    public void getClubByIdTest() {
        UserEntity testUser = userRepository.findByUsername("test");
        // create a test club
        Club club = new Club();
        club.setTitle("Test Club");
        club.setPhotoUrl("https://example.com/test.jpg");
        club.setContent("Test content");
        club.setCity("Test city");
        club.setCreatedBy(testUser);
        clubRepository.save(club);

        ResponseEntity<String> response = restTemplate.getForEntity("/clubs/" + club.getId(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().contains("Test Club"));
        assertTrue(response.getBody().contains("https://example.com/test.jpg"));
    }
}