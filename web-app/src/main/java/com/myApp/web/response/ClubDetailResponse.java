package com.myApp.web.response;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.model.UserEntity;
import lombok.Data;

@Data
public class ClubDetailResponse {
    private ClubDto club;
    private UserEntity user;
}
