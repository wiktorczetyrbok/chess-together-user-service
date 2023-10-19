package chesstogether.web.app.service.impl;

import chesstogether.web.app.dto.EventDto;
import chesstogether.web.app.dto.UserDto;
import chesstogether.web.app.mapper.EventMapper;
import chesstogether.web.app.mapper.UserMapper;
import chesstogether.web.app.model.Club;
import chesstogether.web.app.model.Event;
import chesstogether.web.app.model.UserEntity;
import chesstogether.web.app.repository.ClubRepository;
import chesstogether.web.app.repository.EventRepository;
import chesstogether.web.app.repository.UserRepository;
import chesstogether.web.app.service.EventService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = EventMapper.mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);

    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());

    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return EventMapper.mapToEventDto(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));

        if (!event.getAssignedUsers().isEmpty()) {
            event.getAssignedUsers().forEach(user -> {
                user.getEvents().remove(event);
                userRepository.save(user);
            });
        }

        eventRepository.deleteById(eventId);
    }

    @Override
    public List<EventDto> searchEvents(String query) {
        List<Event> events = eventRepository.searchEvents(query);
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> searchEventsByType(String type) {
        List<Event> events = eventRepository.searchEventsByType(type);
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public void assignUserToEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        List<UserEntity> assignedUsers = event.getAssignedUsers();
        assignedUsers.add(userRepository.findById(userId).get());
        event.setAssignedUsers(assignedUsers);

        List<Event> assignedEvents = user.getEvents();
        assignedEvents.add(event);

        user.setEvents(assignedEvents);

        eventRepository.save(event);
        userRepository.save(user);
    }

    public List<UserDto> findAssignedUsers(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));

        return event.getAssignedUsers()
                .stream()
                .map(user -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }
}
