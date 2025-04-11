package com.example.taskandprojectmanagment.repository;

import com.example.taskandprojectmanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
