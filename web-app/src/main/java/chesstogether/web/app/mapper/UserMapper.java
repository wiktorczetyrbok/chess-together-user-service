package chesstogether.web.app.mapper;

import chesstogether.web.app.dto.UserDto;
import chesstogether.web.app.model.UserEntity;

public class UserMapper {
    public static UserDto mapToUserDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatarUrl(user.getAvatarUrl())
                .rating(user.getRating())
                .roles(user.getRoles())
                .events(user.getEvents())
                .build();

    }

    public static UserEntity mapToUser(UserDto user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatarUrl(user.getAvatarUrl())
                .rating(user.getRating())
                .roles(user.getRoles())
                .events(user.getEvents())
                .build();
    }
}
