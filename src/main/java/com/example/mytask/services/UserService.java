package com.example.mytask.services;

import com.example.mytask.domain.dto.CreateUserDto;
import com.example.mytask.domain.dto.UpdateUserDto;
import com.example.mytask.domain.entities.User;
import com.example.mytask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long createUser (CreateUserDto data) {

        // DTO -> ENTITY
        var entity = new User(null,
                data.name(),
                data.email(),
                data.number(),
                data.username(),
                data.password(),
                null);
        var userSaved = userRepository.save(entity);
        return userSaved.getId();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    public void updateUserById(Long id, UpdateUserDto updateData) {

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if(updateData.username() != null) {
                user.setUsername(updateData.username());
            }

            if(updateData.password() != null) {
                user.setPassword(updateData.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteUserById(Long id) {
        var userExistis = userRepository.existsById(id);

        if (userExistis) {
            userRepository.deleteById(id);
        }
    }
}
