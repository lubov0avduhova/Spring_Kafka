package org.example.corecrm.controller;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.dto.UserDto;
import org.example.corecrm.dto.UserUpdateDto;
import org.example.corecrm.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable(name = "id") Long id) {
        return usersService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return usersService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //todo заменить на dto
    public UserDto updateUser(@PathVariable(name = "id") Long id, @RequestBody UserUpdateDto user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id") Long id) {
        usersService.deleteUser(id);
    }


}
