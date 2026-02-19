package org.example.corecrm.mapping;

import org.example.corecrm.dto.user.UserCreateDto;
import org.example.corecrm.dto.user.UserDto;
import org.example.corecrm.dto.user.UserUpdateDto;
import org.example.corecrm.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public User toEntity(UserCreateDto user) {
        return User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public List<UserDto> toListDto(List<User> list) {
        List<UserDto> result = new ArrayList<>(list.size());
        for (User building : list) {
            result.add(toDto(building));
        }
        return result;
    }

}
