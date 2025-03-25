package com.example.mytask.repositories;

import com.example.mytask.domain.entities.Task;
import com.example.mytask.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
