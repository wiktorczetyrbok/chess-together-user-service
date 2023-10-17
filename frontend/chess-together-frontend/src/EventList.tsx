import{ useState, useEffect } from 'react';
function EventList() {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        // Define the URL of your Event API
        const apiUrl = 'https://api.example.com/events'; // Replace with your API URL

        fetch(apiUrl)
            .then((response) => response.json())
            .then((data) => setEvents(data))
            .catch((error) => console.error('Error fetching data: ', error));
    }, []);

    return (
        <div>
            <h1>Event List</h1>
    <ul>
    {events.map((event) => (
            <li key={event.id}>
                <h2>{event.name}</h2>
                <p>Start Time: {event.startTime}</p>
            <p>End Time: {event.endTime}</p>
            <p>Type: {event.type}</p>
    <p>Photo URL: {event.photoUrl}</p>
    </li>
))}
    </ul>
    </div>
);
}

export default EventList;
