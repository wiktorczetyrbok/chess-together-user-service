package chesstogether.web.app.service;

import chesstogether.web.app.dto.ClubDto;
import chesstogether.web.app.model.Club;

import java.util.List;


public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);

    void delete(long clubId);

    List<ClubDto> searchClubs(String query);
}
