package org.example.corecrm.exception;

public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException(Long id) {
        super("Здание с таким ID не найден: " + id);
    }
}
