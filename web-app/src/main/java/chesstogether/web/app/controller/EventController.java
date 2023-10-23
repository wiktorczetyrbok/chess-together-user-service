package chesstogether.web.app.controller;

import chesstogether.web.app.dto.EventDto;
import chesstogether.web.app.dto.UserDto;
import chesstogether.web.app.model.Event;
import chesstogether.web.app.model.UserEntity;
import chesstogether.web.app.service.EventService;
import chesstogether.web.app.service.UserService;
import chesstogether.web.app.response.EventAssignFormResponse;
import chesstogether.web.app.response.EventCreateFormResponse;
import chesstogether.web.app.response.EventDetailResponse;
import chesstogether.web.app.response.EventListResponse;
import chesstogether.web.app.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<EventListResponse> eventList() {
        EventListResponse response = new EventListResponse();

        List<EventDto> events = eventService.findAllEvents();
        response.setEvents(events);

        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            UserEntity user = userService.findByUsername(username);
            response.setUser(user);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventDto>> searchEvents(@RequestParam(value = "query") String query) {
        List<EventDto> events = eventService.searchEvents(query);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search_by_type")
    public ResponseEntity<List<EventDto>> searchByType(@RequestParam(value = "type") String type) {
        List<EventDto> events = eventService.searchEventsByType(type);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDetailResponse> viewEvent(@PathVariable("eventId") Long eventId) {
        EventDetailResponse response = new EventDetailResponse();

        String username = SecurityUtil.getSessionUser();
        UserEntity user;
        if (username != null) {
            user = userService.findByUsername(username);
            response.setUser(user);
        }

        EventDto eventDto = eventService.findByEventId(eventId);
        List<UserDto> assignedUsers = eventService.findAssignedUsers(eventId);

        response.setClub(eventDto.getClub());
        response.setEvent(eventDto);
        response.setAssignedUsers(assignedUsers);

        return ResponseEntity.ok(response);
    }

    //TODO: check if it should be either ClubID or Club object
    @GetMapping("/{clubId}/new")
    public ResponseEntity<EventCreateFormResponse> createEventForm(@PathVariable("clubId") Long clubId) {
        EventCreateFormResponse response = new EventCreateFormResponse();

        Event event = new Event();
        response.setClubId(clubId);
        response.setEvent(event);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{clubId}")
    public ResponseEntity<String> createEvent(@PathVariable("clubId") Long clubId,
                                              @RequestBody EventDto eventDto
    ) {
        eventService.createEvent(clubId, eventDto);
        return ResponseEntity.ok("Event created successfully");
    }

    @GetMapping("/{eventId}/assignUser")
    public ResponseEntity<EventAssignFormResponse> assignClubForm(@PathVariable("eventId") Long eventId) {
        EventAssignFormResponse response = new EventAssignFormResponse();

        EventDto eventDto = eventService.findByEventId(eventId);
        response.setEvent(eventDto);

        String username = SecurityUtil.getSessionUser();
        UserEntity user;
        if (username != null) {
            user = userService.findByUsername(username);
            response.setUser(user);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{eventId}/assign_user")
    public ResponseEntity<String> updateUserToEvent(@PathVariable("eventId") Long eventId,
                                                    @RequestParam("userId") Long userId) {
        eventService.assignUserToEvent(eventId, userId);
        return ResponseEntity.ok("User assigned to event successfully");
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully");
    }
}