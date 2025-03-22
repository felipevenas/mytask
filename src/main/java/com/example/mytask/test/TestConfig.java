package com.example.mytask.test;

import com.example.mytask.domain.entities.Task;
import com.example.mytask.domain.entities.User;
import com.example.mytask.repositories.TaskRepository;
import com.example.mytask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Felipe", "felipe@gmail.com", "71986760659" ,"felipevenas","123456");
        User u2 = new User(null, "Maria", "maria@gmail.com", "71986266617" ,"mariagomes","123456");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Task t1 = new Task(null, "Study Programming", "Study Java + Spring per 2 hours", false);
        Task t2 = new Task(null, "Pratice Exercice", "Pratice musculation", false);

        taskRepository.saveAll(Arrays.asList(t1, t2));

    }

}
