package com.myApp.web.service.impl;

import com.myApp.web.dto.EventDto;
import com.myApp.web.dto.UserDto;
import com.myApp.web.mapper.EventMapper;
import com.myApp.web.model.Club;
import com.myApp.web.model.Event;
import com.myApp.web.model.UserEntity;
import com.myApp.web.repository.ClubRepository;
import com.myApp.web.repository.EventRepository;
import com.myApp.web.repository.UserRepository;
import com.myApp.web.utils.ModelGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Tag("integration")
@Profile("test")
@SpringBootTest(classes = EventServiceImpl.class)
class EventServiceImplTest {
    @SpyBean
    private EventServiceImpl eventService;

    @MockBean
    private ClubRepository clubRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EventRepository eventRepository;

    private ArrayList<Club> clubs = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();

    private final static String username = "testUser";
    @BeforeEach
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);


        clubs.addAll(ModelGenerator.createClubsForTesting());
        events.addAll(ModelGenerator.createEventsForTesting());
    }
    //TODO: implement querying with concat
    @Test
    void searchEventsByType() {
/*        String eventType = events.get(0).getType();

        when(eventRepository.searchEventsByType(eventType)).thenReturn(events);

        List<EventDto> result = eventService.searchEventsByType(eventType);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        System.out.println(result);
        for (EventDto eventDto : result) {
            assertEquals(eventType, eventDto.getType());
        }*/
    }

    @Test
    void assignUserToEvent() {
        Long eventId = 1L;
        Long userId = 1L;

        Event event = new Event();

        UserEntity user = new UserEntity();

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        eventService.assignUserToEvent(eventId, userId);

        List<UserEntity> assignedUsers = event.getAssignedUsers();
        assertTrue(assignedUsers.contains(user));

        List<Event> assignedEvents = user.getEvents();
        assertTrue(assignedEvents.contains(event));
    }

    @Test
    void findAssignedUsers() {
        Long eventId = 1L;

        Event event = new Event();

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        List<UserDto> assignedUsers = eventService.findAssignedUsers(eventId);

        assertNotNull(assignedUsers);
    }


    @Test
    void deleteEvent_invalidID_test() {
        Long invalidEventId = 999L;

        when(eventRepository.findById(invalidEventId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> eventService.deleteEvent(invalidEventId));
    }

    @Test
    void deleteEvent_validID_test() {
        Long validEventId = 1L;

        Event event = new Event();

        when(eventRepository.findById(validEventId)).thenReturn(Optional.of(event));

        UserEntity user = new UserEntity();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        eventService.deleteEvent(validEventId);

        verify(eventRepository, times(1)).deleteById(any());
    }


    @Test
    void createEvent() {
        Long id = 1L;
        EventDto eventDto = new EventDto();
        when(clubRepository.findById(id)).thenReturn(Optional.ofNullable(clubs.get(0)));
        eventService.createEvent(id, eventDto);
        verify(eventRepository, times(1)).save(any());

    }

    @Test
    void findAllEvents() {
        Mockito.when(eventRepository.findAll()).thenReturn(events);
        List<EventDto> allClubs = eventService.findAllEvents();
        List<EventDto> eventDtos = events.stream().map(EventMapper::mapToEventDto).toList();

        assertEquals(allClubs, eventDtos);
    }

    @Test
    void findByEventId() {
        long eventId = 1L;
        Event event = events.get(0);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        EventDto foundEvent = eventService.findByEventId(eventId);

        assertEquals(event.getId(), foundEvent.getId());
    }


    @Test
    void searchEvents() {
        String query = "test";
        when(eventRepository.searchEvents(query)).thenReturn(events);

        List<EventDto> foundEvents = eventService.searchEvents(query);

        assertEquals(events.size(), foundEvents.size());
    }

}