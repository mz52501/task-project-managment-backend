package com.example.taskandprojectmanagment.service;

import com.example.taskandprojectmanagment.model.Project;
import com.example.taskandprojectmanagment.model.ProjectMember;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public List<Project> getProjectForUser(User user) {
        List<Project> projects = new ArrayList<>(user.getProjects());
        return projects;
    }

    public List<Project> getMemberProjects(User user) {
        /*return user.getProjectMembers().stream()
            .filter(projectMember -> projectMember.getRole().equals("DEVELOPER"))
            .map(ProjectMember::getProject)
            .toList();*/
        return projectRepository.findDeveloperProjectsByUser(user);
    }
}
