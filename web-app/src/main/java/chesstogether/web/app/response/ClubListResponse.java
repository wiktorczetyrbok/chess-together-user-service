package chesstogether.web.app.response;

import chesstogether.web.app.dto.ClubDto;
import chesstogether.web.app.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class ClubListResponse {
    private List<ClubDto> clubs;
    private UserEntity user;
}
