package com.myApp.web.service;

import com.myApp.web.dto.ClubDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {
    List<ClubDto> findAllClubs();
}
