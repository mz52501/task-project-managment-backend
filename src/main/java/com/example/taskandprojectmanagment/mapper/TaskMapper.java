package com.example.taskandprojectmanagment.mapper;

import com.example.taskandprojectmanagment.dto.task.TaskAssigneeDTO;
import com.example.taskandprojectmanagment.dto.task.TaskCreatorDTO;
import com.example.taskandprojectmanagment.dto.task.TaskReq;
import com.example.taskandprojectmanagment.dto.task.TaskRes;
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

    @Mapping(target = "id", source = "id")
    @Mapping(target = "assignedToId", source = "assignedTo.id")
    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "parentTaskId", source = "parentTask.id") // Optional
    @Mapping(target = "parentTaskTitle", source = "parentTask.title") // Optional
    @Mapping(target = "assignedToFirstName", source = "assignedTo.firstName")
    @Mapping(target = "assignedToLastName", source = "assignedTo.lastName")
    @Mapping(target = "createdByFirstName", source = "createdBy.firstName")
    @Mapping(target = "createdByLastName", source = "createdBy.lastName")
    @Mapping(target = "projectName", source = "project.name")
    TaskRes toTaskRes(Task task);

    List<TaskRes> toTaskResList(List<Task> tasks);

    Task toTask(TaskReq taskReq);


}
