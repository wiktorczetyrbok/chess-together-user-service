package com.myApp.web.service;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.model.Club;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(Club club);
    ClubDto findClubById(long clubId);
    void updateClub(ClubDto club);
}
