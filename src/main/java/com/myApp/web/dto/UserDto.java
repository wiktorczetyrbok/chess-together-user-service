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
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private String avatarUrl;
    private Integer rating;
    private List<RoleEntity> roles;
    private List<Event> events;
}
