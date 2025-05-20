package com.example.taskandprojectmanagment.mapper;
import com.example.taskandprojectmanagment.dto.ProjectDTO;
import com.example.taskandprojectmanagment.model.Project;
import com.example.taskandprojectmanagment.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {

    ProjectDTO toProjectDTO(Project project);

    List<ProjectDTO> toProjectsDTO(List<Project> projects);

    @AfterMapping
    default void mapOwner(Project project, @MappingTarget ProjectDTO dto) {
        project.getProjectMembers().stream()
            .filter(member -> "OWNER".equalsIgnoreCase(member.getRole()))
            .findFirst()
            .ifPresent(ownerMember -> {
                User owner = ownerMember.getUser();
                dto.setOwnerId(owner.getId());
                dto.setOwnerFirstName(owner.getFirstName());
                dto.setOwnerLastName(owner.getLastName());
            });
    }
}
