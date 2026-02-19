package org.example.corecrm.mapping;

import org.example.corecrm.dto.UserDto;
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

    public User toEntity(UserDto user) {
        return User.builder()
                .id(user.getId())
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
