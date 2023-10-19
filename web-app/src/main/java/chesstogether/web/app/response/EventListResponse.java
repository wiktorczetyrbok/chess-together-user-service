package chesstogether.web.app.response;

import chesstogether.web.app.dto.EventDto;
import chesstogether.web.app.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class EventListResponse {
    private List<EventDto> events;
    private UserEntity user;
}
