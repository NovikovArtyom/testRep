package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.User;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    private static Map<Integer, User> users = new HashMap<>();
    @GetMapping
    public Collection<User> getUsers() {
        log.debug("Количество пользователей - {}", users.size());
        return users.values();
    }

    @PostMapping
    public User setUser(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        boolean repeatedUser = users.values().stream()
                .anyMatch(item -> item.getEmail().equals(user.getEmail()));
        if (repeatedUser) throw new UserAlreadyExistException("Указанный юзер уже существует!");
        else if (user.getEmail() == null || user.getEmail().isEmpty())
            throw new InvalidEmailException("Некорректное значение email");
        else {
            user.generateId();
            users.put(user.getId(), user);
            return user;
        }
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws InvalidEmailException {
        for (User item : users.values()) {
            if (user.getEmail().equals(item.getEmail())) {
                user.setId(item.getId());
                users.put(user.getId(), user);
                return user;
            }
        }
        if (user.getEmail() == null || user.getEmail().isEmpty())
            throw new InvalidEmailException("Некорректное значение email");
        else {
            user.generateId();
            users.put(user.getId(), user);
            return user;
        }
    }
}
