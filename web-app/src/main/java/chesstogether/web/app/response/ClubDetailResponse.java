package chesstogether.web.app.response;

import chesstogether.web.app.dto.ClubDto;
import chesstogether.web.app.model.UserEntity;
import lombok.Data;

@Data
public class ClubDetailResponse {
    private ClubDto club;
    private UserEntity user;
}
