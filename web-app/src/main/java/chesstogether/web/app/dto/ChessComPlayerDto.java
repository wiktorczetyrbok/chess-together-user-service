package chesstogether.web.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChessComPlayerDto {
    private String username;
    private String rating;
    private String photoUrl;

    public ChessComPlayerDto(String photoUrl, String username, String rating) {
        this.photoUrl = photoUrl;
        this.username = username;
        this.rating = rating;
    }
}
