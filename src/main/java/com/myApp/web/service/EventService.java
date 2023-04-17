package com.myApp.web.service;

import com.myApp.web.dto.EventDto;
import com.myApp.web.dto.UserDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void deleteEvent(Long eventId);
    List<EventDto> searchEvents (String query);
    List<EventDto> searchEventsByType(String type);

    void assignUserToEvent(Long eventId, Long userId);
    List<UserDto> findAssignedUsers(Long eventId);

}
