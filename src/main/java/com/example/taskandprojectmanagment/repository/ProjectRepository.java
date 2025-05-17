package com.example.taskandprojectmanagment.repository;

import com.example.taskandprojectmanagment.model.Project;
import com.example.taskandprojectmanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT pm.project FROM ProjectMember pm WHERE pm.user = :user AND pm.role = 'DEVELOPER'")
    List<Project> findDeveloperProjectsByUser(@Param("user") User user);

}
