package com.myApp.web;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.mapper.ClubMapper;
import com.myApp.web.model.Club;
import com.myApp.web.repository.ClubRepository;
import com.myApp.web.service.ClubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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

    @Test
    public void createClubTest() {
        // create a new club DTO
        Club club = new Club();
        club.setTitle("Test Club");
        club.setPhotoUrl("https://example.com/test.jpg");
        club.setContent("Test content");
        club.setCity("Test city");
        ClubMapper clubMapper = new ClubMapper();
        ClubDto clubDto = clubMapper.mapToClubDto(club);


        // perform POST request to create the new club
        ResponseEntity<Void> response = restTemplate.postForEntity("/clubs", clubDto, Void.class);

        // assert that the response status code is a redirect to /clubs
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().endsWith("/clubs"));

        // retrieve the newly created club from the database
        Optional<Club> clubs = clubRepository.findByTitle("Test Club");
        assertEquals(1, clubs.stream());
        Club createdClub = clubs.get();

        // assert that the club fields match the DTO fields
        assertEquals("Test Club", createdClub.getTitle());
        assertEquals("https://example.com/test.jpg", createdClub.getPhotoUrl());
        assertEquals("Test content", createdClub.getContent());
        assertEquals("Test city", createdClub.getCity());
    }

    @Test
    public void getClubByIdTest() {
        // create a test club
        Club club = new Club();
        club.setTitle("Test Club");
        club.setPhotoUrl("https://example.com/test.jpg");
        club.setContent("Test content");
        club.setCity("Test city");
        clubRepository.save(club);

        ResponseEntity<String> response = restTemplate.getForEntity("/clubs/" + club.getId(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().contains("Test Club"));
        assertTrue(response.getBody().contains("https://example.com/test.jpg"));
    }
}