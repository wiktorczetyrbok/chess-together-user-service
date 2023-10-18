package com.myApp.web.response;

import com.myApp.web.dto.EventDto;
import com.myApp.web.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class EventListResponse {
    private List<EventDto> events;
    private UserEntity user;
}
