package com.example.taskandprojectmanagment.service;

import com.example.taskandprojectmanagment.dto.ProjectDTO;
import com.example.taskandprojectmanagment.mapper.ProjectMapper;
import com.example.taskandprojectmanagment.model.Project;
import com.example.taskandprojectmanagment.model.ProjectMember;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.repository.ProjectMemberRepository;
import com.example.taskandprojectmanagment.repository.ProjectRepository;
import com.example.taskandprojectmanagment.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    final ProjectRepository projectRepository;
    final ProjectMemberRepository projectMemberRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, ProjectMapper projectMapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }

    public List<ProjectDTO> getProjectForUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return projectMapper.toProjectsDTO(
            user.getProjectMembers().stream()
                .filter(projectMember -> projectMember.getRole().equals("OWNER"))
                .map(ProjectMember::getProject)
                .toList()
        );
    }

    public List<ProjectDTO> getMemberProjects(Integer userId) {
        //return projectMapper.toProjectsDTO(projectMemberRepository.findProjectsByUser(user));
        User user = userRepository.findById(userId).orElseThrow();
        return projectMapper.toProjectsDTO(
            user.getProjectMembers().stream()
                .filter(projectMember -> !projectMember.getRole().equals("OWNER"))
                .map(ProjectMember::getProject)
                .toList()
        );
    }
}
