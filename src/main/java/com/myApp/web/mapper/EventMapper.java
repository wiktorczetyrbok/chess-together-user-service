package com.myApp.web.mapper;

import com.myApp.web.dto.EventDto;
import com.myApp.web.model.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto){
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();

    }
}
