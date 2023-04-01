package com.myApp.web.service.impl;

import com.myApp.web.dto.EventDto;
import com.myApp.web.model.Club;
import com.myApp.web.model.Event;
import com.myApp.web.repository.ClubRepository;
import com.myApp.web.repository.EventRepository;
import com.myApp.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.myApp.web.mapper.EventMapper.mapToEvent;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);

    }

}
