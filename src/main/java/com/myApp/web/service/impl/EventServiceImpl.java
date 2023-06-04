package com.myApp.web.service.impl;

import com.myApp.web.dto.EventDto;
import com.myApp.web.dto.UserDto;
import com.myApp.web.model.Club;
import com.myApp.web.model.Event;
import com.myApp.web.model.UserEntity;
import com.myApp.web.repository.ClubRepository;
import com.myApp.web.repository.EventRepository;
import com.myApp.web.repository.UserRepository;
import com.myApp.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.myApp.web.mapper.EventMapper.mapToEvent;
import static com.myApp.web.mapper.EventMapper.mapToEventDto;
import static com.myApp.web.mapper.UserMapper.mapToUserDto;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;
    private UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
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
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));

        if ( !event.getAssignedUsers().isEmpty()) {
            List<UserEntity> users = event.getAssignedUsers();
            for (UserEntity user : users) {
                user.getEvents().remove(event);
                userRepository.save(user);
            }
        }

        eventRepository.deleteById(eventId);
    }
    @Override
    public List<EventDto> searchEvents(String query) {
        List<Event> events = eventRepository.searchEvents(query);
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }
    @Override
    public List<EventDto> searchEventsByType(String type) {
        List<Event> events = eventRepository.searchEventsByType(type);
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public void assignUserToEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        List<UserEntity> assignedUsers;
        assignedUsers = event.getAssignedUsers();
        assignedUsers.add(userRepository.findById(userId).get());
        event.setAssignedUsers(assignedUsers);

        List<Event> assignedEvents;
        assignedEvents = user.getEvents();
        assignedEvents.add(event);

        user.setEvents(assignedEvents);

        eventRepository.save(event);
        userRepository.save(user);
    }
    public List<UserDto> findAssignedUsers(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));

        List<UserDto> assignedUsers = event.getAssignedUsers()
                .stream()
                .map(user -> mapToUserDto(user)).collect(Collectors.toList());

        return assignedUsers;
    }
}
