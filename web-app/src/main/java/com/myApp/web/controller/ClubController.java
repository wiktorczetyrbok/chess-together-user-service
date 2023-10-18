package com.myApp.web.controller;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.model.Club;
import com.myApp.web.model.UserEntity;
import com.myApp.web.response.ClubCreateFormResponse;
import com.myApp.web.response.ClubDetailResponse;
import com.myApp.web.response.ClubListResponse;
import com.myApp.web.security.SecurityUtil;
import com.myApp.web.service.ClubService;
import com.myApp.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;
    private final UserService userService;

    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }


    @GetMapping("/")
    public ResponseEntity<ClubListResponse> listClubs() {
        ClubListResponse response = new ClubListResponse();

        List<ClubDto> clubs = clubService.findAllClubs();
        response.setClubs(clubs);

        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            UserEntity user = userService.findByUsername(username);
            response.setUser(user);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDetailResponse> clubDetails(@PathVariable("clubId") long clubId) {
        ClubDetailResponse response = new ClubDetailResponse();

        ClubDto clubDto = clubService.findClubById(clubId);
        response.setClub(clubDto);

        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            UserEntity user = userService.findByUsername(username);
            response.setUser(user);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/new")
    public ResponseEntity<ClubCreateFormResponse> createClubForm() {
        ClubCreateFormResponse response = new ClubCreateFormResponse();
        Club club = new Club();
        response.setClub(club);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveClub(@Valid @RequestBody ClubDto clubDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }

        clubService.saveClub(clubDto);

        return ResponseEntity.ok("Club created successfully");
    }

    @DeleteMapping("/{clubId}")
    public ResponseEntity<String> deleteClub(@PathVariable("clubId") long clubId) {
        clubService.delete(clubId);
        return ResponseEntity.ok("Club deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClubDto>> searchClub(@RequestParam(value = "query") String query) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/{clubId}/edit")
    public ResponseEntity<ClubDto> editClubForm(@PathVariable("clubId") Long clubId) {
        ClubDto club = clubService.findClubById(clubId);
        return ResponseEntity.ok(club);
    }

    @PostMapping("/{clubId}/edit")
    public ResponseEntity<String> updateClub(
            @PathVariable("clubId") Long clubId,
            @Valid @RequestBody ClubDto club,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }

        club.setId(clubId);
        clubService.updateClub(club);

        return ResponseEntity.ok("Club updated successfully");
    }
}