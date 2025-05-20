package com.example.taskandprojectmanagment.repository;

import com.example.taskandprojectmanagment.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
