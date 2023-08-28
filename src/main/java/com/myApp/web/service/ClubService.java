package com.myApp.web.service;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.model.Club;

import java.util.List;


public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);

    void delete(long clubId);

    List<ClubDto> searchClubs(String query);
}
