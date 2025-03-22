package com.example.mytask.services;

import com.example.mytask.domain.entities.Task;
import com.example.mytask.domain.entities.User;
import com.example.mytask.repositories.TaskRepository;
import com.example.mytask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> obj = taskRepository.findById(id);
        return obj.get();
    }
}
