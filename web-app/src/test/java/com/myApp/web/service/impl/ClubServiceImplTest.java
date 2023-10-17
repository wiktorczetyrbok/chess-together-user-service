package com.myApp.web.service.impl;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.mapper.ClubMapper;
import com.myApp.web.model.Club;
import com.myApp.web.model.UserEntity;
import com.myApp.web.repository.ClubRepository;
import com.myApp.web.repository.UserRepository;
import com.myApp.web.utils.ModelGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Tag("integration")
@SpringBootTest(classes = {ClubServiceImpl.class})
class ClubServiceImplTest {

    @MockBean
    private ClubRepository clubRepository;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private ClubServiceImpl clubService;

    private final static String username = "testUser";

    private final ArrayList<Club> clubs = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);


        clubs.addAll(ModelGenerator.createClubsForTesting());
    }

    @Test
    void findAllClubs_test() {
        Mockito.when(clubRepository.findAll()).thenReturn(clubs);
        List<ClubDto> allClubs = clubService.findAllClubs();
        List<ClubDto> clubDtos = clubs.stream().map(ClubMapper::mapToClubDto).toList();

        assertEquals(allClubs, clubDtos);
    }

    @Test
    void saveClub() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        Club testClubEntity = clubs.get(0);
        testClubEntity.setCreatedBy(userEntity);

        ClubDto clubDto = ClubMapper.mapToClubDto(testClubEntity);

        when(clubRepository.save(Mockito.any(Club.class))).thenReturn(testClubEntity);

        Club savedClub = clubService.saveClub(clubDto);

        assertEquals(clubDto.getTitle(), savedClub.getTitle());
        assertEquals(clubDto.getCity(), savedClub.getCity());
        assertEquals(userEntity, savedClub.getCreatedBy());
    }

    @Test
    void updateClub() {

        ClubDto clubDto = new ClubDto();
        clubDto.setId(1L);
        clubDto.setTitle("Updated Club");
        clubDto.setCity("Updated City");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(userEntity);
        when(clubRepository.save(Mockito.any(Club.class))).thenReturn(clubs.get(0));

        clubService.updateClub(clubDto);

    }

    @Test
    void delete() {
        when(clubRepository.findById(1L)).thenReturn(Optional.of(clubs.get(0)));

        clubService.delete(1L);

        Mockito.verify(clubRepository).deleteById(1L);
    }

    //TODO: implement querying with concat
    @Test
    void searchClubs() {
        String query = "test";
        when(clubRepository.searchClubs(query)).thenReturn(clubs);

        List<ClubDto> foundClubs = clubService.searchClubs(query);

        assertEquals(clubs.size(), foundClubs.size());
    }

    @Test
    void findClubById() {
        long clubId = 1L;
        Club club = clubs.get(0);
        when(clubRepository.findById(clubId)).thenReturn(Optional.of(club));

        ClubDto foundClub = clubService.findClubById(clubId);

        assertEquals(club.getId(), foundClub.getId());
    }
}
