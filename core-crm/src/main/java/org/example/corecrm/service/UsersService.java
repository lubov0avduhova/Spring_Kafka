package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.dto.UserDto;
import org.example.corecrm.dto.UserUpdateDto;
import org.example.corecrm.entity.User;
import org.example.corecrm.exception.UserNotFoundException;
import org.example.corecrm.mapping.UsersMapper;
import org.example.corecrm.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper mapper;

    public UserDto getUserById(Long userId) {
        return mapper.toDto(usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
    }

    public List<UserDto> findAllUsers() {
        return mapper.toListDto(usersRepository.findAll());
    }

    public UserDto createUser(UserDto user) {
        return mapper.toDto(usersRepository.save(mapper.toEntity(user)));
    }

    public UserDto updateUser(Long id, UserUpdateDto user) {
        return mapper.toDto(usersRepository.findById(id)
                .map(existingUser ->
                        usersRepository.save(updateByDto(user))
                )
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }


    private User updateByDto(UserUpdateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
