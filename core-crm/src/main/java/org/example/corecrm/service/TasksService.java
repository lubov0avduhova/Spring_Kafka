package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.dto.TaskDto;
import org.example.corecrm.dto.TaskUpdateDto;
import org.example.corecrm.entity.Task;
import org.example.corecrm.exception.TaskNotFoundException;
import org.example.corecrm.mapping.TaskMapper;
import org.example.corecrm.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final TaskMapper mapper;

    public TaskDto getTaskById(Long id) {
        return mapper.toDto(tasksRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id)));
    }

    public List<TaskDto> findAllTasks() {
        return mapper.toListDto(tasksRepository.findAll());
    }

    public TaskDto createTask(TaskDto task) {
        return mapper.toDto(tasksRepository.save(mapper.toEntity(task)));
    }

    public TaskDto updateTask(Long id, TaskUpdateDto task) {
        return mapper.toDto(tasksRepository.findById(id)
                .map(existingTask ->
                        tasksRepository.save(updateByDto(task))
                )
                .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    public void deleteTask(Long id) {
        tasksRepository.deleteById(id);
    }

    private Task updateByDto(TaskUpdateDto dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setClosed(dto.isClosed());
        task.setCreatedAt(dto.getCreatedAt());
        return task;
    }
}
