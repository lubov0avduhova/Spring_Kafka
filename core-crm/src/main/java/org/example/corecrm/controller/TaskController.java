package org.example.corecrm.controller;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.entity.Task;
import org.example.corecrm.service.TasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TasksService tasksService;

    @GetMapping("/{id}")
    //todo заменить на dto
    public Task getTaskById(@PathVariable(name = "id") Long id) {
        return tasksService.getTaskById(id);
    }

    @GetMapping
    //todo заменить на dto
    public List<Task> getAllTasks() {
        return tasksService.findAllTasks();
    }

    @PostMapping
    //todo заменить на dto
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(tasksService.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //todo заменить на dto
    public Task updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {
        return tasksService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable(name = "id") Long id) {
        tasksService.deleteTask(id);
    }
}
