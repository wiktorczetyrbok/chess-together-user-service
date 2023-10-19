package chesstogether.web.app.response;

import chesstogether.web.app.dto.EventDto;
import chesstogether.web.app.model.UserEntity;
import lombok.Data;

@Data
public class EventAssignFormResponse {
    private UserEntity user;
    private EventDto event;
}
