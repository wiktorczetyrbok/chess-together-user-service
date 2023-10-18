package com.myApp.web.response;

import com.myApp.web.dto.EventDto;
import com.myApp.web.model.UserEntity;
import lombok.Data;

@Data
public class EventAssignFormResponse {
    private UserEntity user;
    private EventDto event;
}
