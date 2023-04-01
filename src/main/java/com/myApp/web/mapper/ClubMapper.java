package com.myApp.web.mapper;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.model.Club;

public class ClubMapper {
    public static Club mapToClub(ClubDto club) {
        Club clubMaped = Club.builder()
                .id(club.getId())
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubMaped;
    }
    public static ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }
}
