package chesstogether.web.app.response;

import chesstogether.web.app.dto.EventDto;
import chesstogether.web.app.dto.UserDto;
import chesstogether.web.app.model.Club;
import chesstogether.web.app.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class EventDetailResponse {
    private EventDto event;
    private UserEntity user;
    private List<UserDto> assignedUsers;
    private Club club;
}
