package com.myApp.web.response;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class ClubListResponse {
    private List<ClubDto> clubs;
    private UserEntity user;
}
