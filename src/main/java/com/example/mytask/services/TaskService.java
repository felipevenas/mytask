package com.example.mytask.services;

import com.example.mytask.domain.dto.CreateTaskDto;
import com.example.mytask.domain.dto.UpdateTaskBodyDto;
import com.example.mytask.domain.dto.UpdateTaskStatusDto;
import com.example.mytask.domain.entities.Task;
import com.example.mytask.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Long createTask(CreateTaskDto data) {

        var task = new Task(null,
                data.title(),
                data.description(),
                false,
                null);

        var taskSaved = taskRepository.save(task);
        return taskSaved.getId();
    }

    public Task findTaskById(Long taskId) {

        Optional<Task> task = taskRepository.findById(taskId);
        return task.get();
    }

    public List<Task> listTasks() {

        return taskRepository.findAll();
    }

    public void deleteById(Long taskId) {

        var taskExistis = taskRepository.existsById(taskId);
        if (taskExistis) {
            taskRepository.deleteById(taskId);
        }
    }

    public void updateTaskBodyById(Long taskId, UpdateTaskBodyDto updateData) {

        var taskEntity = taskRepository.findById(taskId);

        if (taskEntity.isPresent()) {
            var task = taskEntity.get();

            if(updateData.title() != null) {
                task.setTitle(updateData.title());
            }

            if(updateData.description() != null) {
                task.setDescription(updateData.description());
            }

            taskRepository.save(task);
        }
    }

    public void updateTaskStatusById(Long taskId, UpdateTaskStatusDto updatedStatus) {

        var taskEntity = taskRepository.findById(taskId);

        if (taskEntity.isPresent()) {
            var task = taskEntity.get();

            task.setCheckbox(updatedStatus.checkbox());

            taskRepository.save(task);
        }
    }
}
