package com.myApp.web.service;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.dto.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void deleteEvent(Long eventId);
    List<EventDto> searchEvents (String query);

}
