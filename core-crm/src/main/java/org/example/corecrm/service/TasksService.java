package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.dto.task.TaskCreateDto;
import org.example.corecrm.dto.task.TaskDto;
import org.example.corecrm.dto.task.TaskUpdateDto;
import org.example.corecrm.entity.Building;
import org.example.corecrm.entity.Task;
import org.example.corecrm.entity.User;
import org.example.corecrm.exception.BuildingNotFoundException;
import org.example.corecrm.exception.TaskNotFoundException;
import org.example.corecrm.exception.UserNotFoundException;
import org.example.corecrm.mapping.TaskMapper;
import org.example.corecrm.repository.BuildingRepository;
import org.example.corecrm.repository.TaskRepository;
import org.example.corecrm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;
    private final TaskMapper mapper;

    public TaskDto getTaskById(Long id) {
        return mapper.toDto(taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id)));
    }

    public List<TaskDto> findAllTasks() {
        return mapper.toListDto(taskRepository.findAll());
    }

    public TaskDto createTask(TaskCreateDto task) {
        User user = userRepository.findById(task.getUserId())
                .orElseThrow(() -> new UserNotFoundException(task.getUserId()));

        Building building = buildingRepository.findById(task.getBuildingId())
                .orElseThrow(() -> new BuildingNotFoundException(task.getBuildingId()));

        Task entity = mapper.toEntity(task);
        entity.setUser(user);
        entity.setBuilding(building);

        return mapper.toDto(taskRepository.save(entity));
    }

    public TaskDto updateTask(Long id, TaskUpdateDto task) {
        return mapper.toDto(taskRepository.findById(id)
                .map(existingTask ->
                        taskRepository.save(updateByDto(task))
                )
                .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private Task updateByDto(TaskUpdateDto dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setClosed(dto.isClosed());
        task.setCreatedAt(dto.getCreatedAt());
        return task;
    }
}
