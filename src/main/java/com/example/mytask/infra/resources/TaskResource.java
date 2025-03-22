package com.example.mytask.infra.resources;

import com.example.mytask.domain.entities.Task;
import com.example.mytask.domain.entities.User;
import com.example.mytask.services.TaskService;
import com.example.mytask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity <List<Task>> findAll() {
        List<Task> list = taskService.findAll();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<Task> findById(Long id) {
        Task obj = taskService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
