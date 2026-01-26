package org.example.corecrm.controller;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.entity.User;
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
    //todo заменить на dto
    public User getUserById(@PathVariable(name = "id") Long id) {
        return usersService.getUserById(id);
    }

    @GetMapping
    //todo заменить на dto
    public List<User> getAllUsers() {
        return usersService.findAllUsers();
    }

    @PostMapping
    //todo заменить на dto
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //todo заменить на dto
    public User updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id") Long id) {
        usersService.deleteUser(id);
    }


}
