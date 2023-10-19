package chesstogether.web.app.service;

import chesstogether.web.app.dto.RegistrationDto;
import chesstogether.web.app.dto.UserDto;
import chesstogether.web.app.model.UserEntity;

import java.util.Optional;


public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    Optional<UserEntity> findByUserId(Long userId);

    void updateUser(UserDto user);
}
