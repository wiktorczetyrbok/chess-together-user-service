package com.myApp.web.service;

import com.myApp.web.dto.EventDto;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
