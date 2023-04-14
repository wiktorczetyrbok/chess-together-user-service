package com.myApp.web.service;

import com.myApp.web.dto.RegistrationDto;
import com.myApp.web.dto.UserDto;
import com.myApp.web.model.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
    Optional<UserEntity> findByUserId(Long userId);

    void updateUser(UserDto user);
}
