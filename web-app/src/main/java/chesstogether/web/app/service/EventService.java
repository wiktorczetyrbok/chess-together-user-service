package chesstogether.web.app.service;

import chesstogether.web.app.dto.EventDto;
import chesstogether.web.app.dto.UserDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void deleteEvent(Long eventId);

    List<EventDto> searchEvents(String query);

    List<EventDto> searchEventsByType(String type);

    void assignUserToEvent(Long eventId, Long userId);

    List<UserDto> findAssignedUsers(Long eventId);

}
