package com.example.mytask.infra.resources;

import com.example.mytask.domain.dto.CreateUserDto;
import com.example.mytask.domain.dto.UpdateUserDto;
import com.example.mytask.domain.entities.User;
import com.example.mytask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Validated CreateUserDto data) {
        var userId = userService.createUser(data);
        return ResponseEntity.created(URI.create("/users/" + userId)).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Long userId) {
        var user = userService.findUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") Long userId,
                                                 @RequestBody UpdateUserDto updateData) {

        userService.updateUserById(userId, updateData);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
