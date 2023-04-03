package com.myApp.web.service;

import com.myApp.web.dto.RegistrationDto;
import com.myApp.web.model.UserEntity;


public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
