package com.myApp.web.service.impl;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.dto.EventDto;
import com.myApp.web.model.Club;
import com.myApp.web.model.Event;
import com.myApp.web.repository.ClubRepository;
import com.myApp.web.repository.EventRepository;
import com.myApp.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.myApp.web.mapper.ClubMapper.mapToClubDto;
import static com.myApp.web.mapper.EventMapper.mapToEvent;
import static com.myApp.web.mapper.EventMapper.mapToEventDto;

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

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList());

    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
    @Override
    public List<EventDto> searchEvents(String query) {
        List<Event> events = eventRepository.searchEvents(query);
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }
}
