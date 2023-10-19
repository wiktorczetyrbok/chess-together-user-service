package chesstogether.web.app.repository;

import chesstogether.web.app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    void deleteById(Long eventId);

    @Query("SELECT e FROM Event e WHERE e.name LIKE CONCAT ('%', :query, '%')")
    List<Event> searchEvents(String query);

    @Query("SELECT e FROM Event e WHERE e.type = :type")
    List<Event> searchEventsByType(String type);
}
