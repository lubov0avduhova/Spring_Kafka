package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.entity.User;
import org.example.corecrm.exception.UserNotFoundException;
import org.example.corecrm.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public User getUserById(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public List<User> findAllUsers() {
        return usersRepository.findAll();
    }

    public User createUser(User user) {
        return usersRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        return usersRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());

                    return usersRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
