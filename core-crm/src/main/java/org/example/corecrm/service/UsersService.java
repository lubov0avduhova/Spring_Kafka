package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.dto.user.UserCreateDto;
import org.example.corecrm.dto.user.UserDto;
import org.example.corecrm.dto.user.UserUpdateDto;
import org.example.corecrm.entity.Building;
import org.example.corecrm.entity.Task;
import org.example.corecrm.entity.User;
import org.example.corecrm.exception.BuildingNotFoundException;
import org.example.corecrm.exception.TaskNotFoundException;
import org.example.corecrm.exception.UserNotFoundException;
import org.example.corecrm.mapping.UsersMapper;
import org.example.corecrm.repository.BuildingRepository;
import org.example.corecrm.repository.TaskRepository;
import org.example.corecrm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final BuildingRepository buildingRepository;
    private final UsersMapper mapper;

    public UserDto getUserById(Long userId) {
        return mapper.toDto(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
    }

    public List<UserDto> findAllUsers() {
        return mapper.toListDto(userRepository.findAll());
    }

    public UserDto createUser(UserCreateDto user) {
        Task task = taskRepository.findById(user.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(user.getTaskId()));

        Building building = buildingRepository.findById(user.getBuildingId())
                .orElseThrow(() -> new BuildingNotFoundException(user.getBuildingId()));

        User entity = mapper.toEntity(user);

        entity.setTasks(List.of(task));
        entity.setBuildings(List.of(building));

        User savedUser = userRepository.save(entity);

        return mapper.toDto(savedUser);
    }

    public UserDto updateUser(Long id, UserUpdateDto user) {
        return mapper.toDto(taskRepository.findById(id)
                .map(existingUser ->
                        userRepository.save(updateByDto(user))
                )
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    private User updateByDto(UserUpdateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
