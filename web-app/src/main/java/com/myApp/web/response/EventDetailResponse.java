package com.myApp.web.response;

import com.myApp.web.dto.EventDto;
import com.myApp.web.dto.UserDto;
import com.myApp.web.model.Club;
import com.myApp.web.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class EventDetailResponse {
    private EventDto event;
    private UserEntity user;
    private List<UserDto> assignedUsers;
    private Club club;
}
