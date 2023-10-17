package com.myApp.web.utils;

import com.myApp.web.model.Club;
import com.myApp.web.model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModelGenerator {
    public static List<Club> createClubsForTesting() {
        List<Club> clubs = new ArrayList<>();

        Club club1 = new Club();
        club1.setTitle("Chess Club");
        club1.setPhotoUrl("https://example.com/chess.jpg");
        club1.setContent("A club for chess enthusiasts.");
        club1.setCity("New York");

        Club club2 = new Club();
        club2.setTitle("Hiking Club");
        club2.setPhotoUrl("https://example.com/hiking.jpg");
        club2.setContent("Explore the great outdoors.");
        club2.setCity("Denver");

        clubs.add(club1);
        clubs.add(club2);

        return clubs;
    }

    public static List<Event> createEventsForTesting() {
        List<Event> events = new ArrayList<>();

        Event event1 = new Event();
        event1.setName("Chess Tournament");
        event1.setStartTime(LocalDateTime.of(2023, 10, 20, 14, 0));
        event1.setEndTime(LocalDateTime.of(2023, 10, 20, 17, 0));
        event1.setType("Chess");
        event1.setPhotoUrl("https://example.com/chess-event.jpg");

        Event event2 = new Event();
        event2.setName("Mountain Hike");
        event2.setStartTime(LocalDateTime.of(2023, 11, 5, 9, 0));
        event2.setEndTime(LocalDateTime.of(2023, 11, 5, 16, 0));
        event2.setType("Hiking");
        event2.setPhotoUrl("https://example.com/hiking-event.jpg");

        events.add(event1);
        events.add(event2);

        return events;
    }



}
