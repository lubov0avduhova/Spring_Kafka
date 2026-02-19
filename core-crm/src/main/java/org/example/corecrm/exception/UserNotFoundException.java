package org.example.corecrm.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Пользователь с таким ID не найден: " + id);
    }
}
