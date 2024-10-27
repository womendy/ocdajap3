package com.oc.rental.mapper;

import com.oc.rental.dto.UserDto;
import com.oc.rental.models.User;

import java.util.Optional;

public class UserMapper {

    public static Optional<UserDto> toDto(Optional<User> userOpt) {
        return userOpt.map(user -> {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setCreated_at(user.getCreated_at());
            dto.setUpdated_at(user.getUpdated_at());
            return dto;
        });
    }

    public static Optional<User> toEntity(Optional<UserDto> userDtoOpt) {
        return userDtoOpt.map(userDto -> {
            User user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setCreated_at(userDto.getCreated_at());
            user.setUpdated_at(userDto.getUpdated_at());
            return user;
        });
    }
}
