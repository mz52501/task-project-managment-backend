package com.example.taskandprojectmanagment.mapper;

import com.example.taskandprojectmanagment.dto.TaskAssigneeDTO;
import com.example.taskandprojectmanagment.dto.TaskDTO;
import com.example.taskandprojectmanagment.dto.TaskCreatorDTO;
import com.example.taskandprojectmanagment.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    /*default List<TaskDTO> toTaskDTOs(List<Task> tasks){
        if (tasks.isEmpty()){
            return List.of();
        }
        return tasks.stream().map(this::toTaskDTO).collect(Collectors.toList());
    }*/

    @Named("toTaskCreatorDTO")
    @Mapping(target = "createdByFirstName", source = "createdBy.firstName")
    @Mapping(target = "createdByLastName", source = "createdBy.lastName")
    TaskCreatorDTO toTaskCreatorDTO(Task task);

    @IterableMapping(qualifiedByName = "toTaskCreatorDTO")
    List<TaskCreatorDTO> toTaskCreatorDTOs(List<Task> tasks);

    // For simple DTO (basic info only)
    @Named("toTaskAssigneeDTO")
    @Mapping(target = "assignedToFirstName", source = "assignedTo.firstName")
    @Mapping(target = "assignedToLastName", source = "assignedTo.lastName")
    TaskAssigneeDTO toTaskAssigneeDTO(Task task);

    @IterableMapping(qualifiedByName = "toTaskAssigneeDTO")
    List<TaskAssigneeDTO> toTaskAssigneeDTOs(List<Task> tasks);
}
