package com.example.taskandprojectmanagment.controller;

import com.example.taskandprojectmanagment.dto.ProjectDTO;
import com.example.taskandprojectmanagment.mapper.ProjectMapper;
import com.example.taskandprojectmanagment.model.Project;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.security.CustomUserDetailsService;
import com.example.taskandprojectmanagment.security.UserDetailsImpl;
import com.example.taskandprojectmanagment.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {

    private final ProjectMapper projectMapper;
    private final ProjectService projectService;

    public ProjectController(ProjectMapper projectMapper, ProjectService projectService) {
        this.projectMapper = projectMapper;
        this.projectService = projectService;
    }

    //projects that I created, OWNER
    @GetMapping("/projects/mine")
    public ResponseEntity<List<ProjectDTO>> getMyProjects(Authentication authentication) {
        User user  =((UserDetailsImpl) authentication.getPrincipal()).getUser();
        return ResponseEntity.ok(projectService.getProjectForUser(user.getId()));
    }

    //projects that I am member of, MEMBER
    @GetMapping("/projects/member")
    public ResponseEntity<List<ProjectDTO>> getProjectsMember(Authentication authentication) {
        User user  =((UserDetailsImpl) authentication.getPrincipal()).getUser();
        return ResponseEntity.ok(projectService.getMemberProjects(user.getId()));
    }

}
