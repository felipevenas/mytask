package com.example.mytask.infra.resources;

import com.example.mytask.domain.dto.CreateTaskDto;
import com.example.mytask.domain.dto.UpdateTaskBodyDto;
import com.example.mytask.domain.dto.UpdateTaskStatusDto;
import com.example.mytask.domain.entities.Task;
import com.example.mytask.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Validated CreateTaskDto data) {
        var taskId = taskService.createTask(data);
        return ResponseEntity.created(URI.create("/tasks/" + taskId)).build();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> findTaskById(@PathVariable("taskId") Long taskId) {
        var task = taskService.findTaskById(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);    // Retorna a 'Task'
        }
        return ResponseEntity.notFound().build();  // Caso não exista 'Task' com aquele ID
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {
        var tasks = taskService.listTasks();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable Long taskId) {
        taskService.deleteById(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Void> updateTaskBodyById(@PathVariable("taskId") Long taskId,
                                                   @RequestBody UpdateTaskBodyDto updatedBody) {

        taskService.updateTaskBodyById(taskId, updatedBody);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTaskStatusById(@PathVariable("taskId") Long taskId,
                                                    @RequestBody UpdateTaskStatusDto updatedStatus) {

        taskService.updateTaskStatusById(taskId, updatedStatus);
        return ResponseEntity.ok().build();
    }
}
