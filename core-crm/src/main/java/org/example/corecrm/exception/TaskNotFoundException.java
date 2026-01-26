package org.example.corecrm.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Задача с таким ID не найдена: " + id);
    }
}
