package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.entity.Task;
import org.example.corecrm.exception.TaskNotFoundException;
import org.example.corecrm.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;

    public Task getTaskById(Long id) {
        return tasksRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> findAllTasks() {
        return tasksRepository.findAll();
    }

    public Task createTask(Task task) {
        return tasksRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        return tasksRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setName(task.getName());
                    existingTask.setClosed(task.isClosed());
                    existingTask.setCreatedAt(task.getCreatedAt());

                    return tasksRepository.save(existingTask);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteTask(Long id) {
        tasksRepository.deleteById(id);
    }
}
