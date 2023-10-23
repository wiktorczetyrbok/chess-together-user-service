-- Insert data into the clubs table
INSERT INTO clubs (title, photoUrl, content, city, createdOn, updatedOn, created_by)
VALUES
    ('Club 1', 'club1.jpg', 'Club 1 Description', 'City 1', NOW(), NOW(), 1),
    ('Club 2', 'club2.jpg', 'Club 2 Description', 'City 2', NOW(), NOW(), 2);

-- Insert data into the events table
INSERT INTO events (name, startTime, endTime, type, photoUrl, createdOn, updatedOn, club_id)
VALUES
    ('Event 1', '2023-10-20 10:00:00', '2023-10-20 12:00:00', 'Type A', 'event1.jpg', NOW(), NOW(), 1),
    ('Event 2', '2023-10-21 14:00:00', '2023-10-21 16:00:00', 'Type B', 'event2.jpg', NOW(), NOW(), 2);
