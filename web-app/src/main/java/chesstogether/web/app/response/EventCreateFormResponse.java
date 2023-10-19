package chesstogether.web.app.response;

import chesstogether.web.app.model.Event;
import lombok.Data;

@Data
public class EventCreateFormResponse {
    private Long clubId;
    private Event event;
}
