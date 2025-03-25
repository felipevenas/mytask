package com.example.mytask.infra.resources;

import com.example.mytask.domain.dto.RequestUserDto;
import com.example.mytask.domain.entities.User;
import com.example.mytask.repositories.UserRepository;
import com.example.mytask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity <List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<User> findById(Long id) {
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> newUser(@RequestBody @Validated RequestUserDto data) {
        User user = new User(null, data.name(), data.email(), data.number(), data.username(), data.password(), null);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

}
