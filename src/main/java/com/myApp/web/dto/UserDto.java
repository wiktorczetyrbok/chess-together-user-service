package com.myApp.web.dto;

import com.myApp.web.model.Event;
import com.myApp.web.model.RoleEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private Integer rating;
    private List<RoleEntity> roles;
    private List<Event> events;
}
