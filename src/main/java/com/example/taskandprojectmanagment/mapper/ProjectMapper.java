package com.example.taskandprojectmanagment.mapper;
import com.example.taskandprojectmanagment.dto.ProjectDTO;
import com.example.taskandprojectmanagment.model.Project;
import com.example.taskandprojectmanagment.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "ownerFirstName", source = "owner.firstName")
    @Mapping(target = "ownerLastName", source = "owner.lastName")
    ProjectDTO toProjectDTO(Project project);

    List<ProjectDTO> toProjectsDTO(List<Project> projects);
}
