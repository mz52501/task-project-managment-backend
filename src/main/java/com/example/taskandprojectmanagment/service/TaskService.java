package com.example.taskandprojectmanagment.service;

import com.example.taskandprojectmanagment.dto.*;
import com.example.taskandprojectmanagment.dto.task.TaskAssigneeDTO;
import com.example.taskandprojectmanagment.dto.task.TaskCreatorDTO;
import com.example.taskandprojectmanagment.dto.task.TaskReq;
import com.example.taskandprojectmanagment.dto.task.TaskRes;
import com.example.taskandprojectmanagment.mapper.TaskMapper;
import com.example.taskandprojectmanagment.mapper.TimeTrackingMapper;
import com.example.taskandprojectmanagment.model.Project;
import com.example.taskandprojectmanagment.model.Task;
import com.example.taskandprojectmanagment.model.TimeTracking;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.repository.ProjectRepository;
import com.example.taskandprojectmanagment.repository.TaskRepository;
import com.example.taskandprojectmanagment.repository.TimeTrackingRepository;
import com.example.taskandprojectmanagment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final TimeTrackingRepository timeTrackingRepository;
    private final TimeTrackingMapper timeTrackingMapper;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper, TimeTrackingRepository timeTrackingRepository, TimeTrackingMapper timeTrackingMapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
        this.timeTrackingRepository = timeTrackingRepository;
        this.timeTrackingMapper = timeTrackingMapper;
        this.projectRepository = projectRepository;
    }

    public List<TaskCreatorDTO> getAllTasksForDeveloper(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return taskMapper.toTaskCreatorDTOs(user.getTasks().stream().toList());
    }

    public List<TaskAssigneeDTO> getAllTasksForCreator(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return taskMapper.toTaskAssigneeDTOs(user.getCreatedTasks().stream().toList());
    }

    public TimeTrackingRes setTrackingEntry(TimeTrackingReq req) {
        TimeTracking timeTracking = timeTrackingMapper.toTimeTracking(req);
        User user = userRepository.findById(req.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Task task = taskRepository.findById(req.getTaskId())
            .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        timeTracking.setUser(user);
        timeTracking.setTask(task);
        timeTracking = timeTrackingRepository.save(timeTracking);
        return timeTrackingMapper.toTimeRes(timeTracking);
    }

    public List<TimeTrackingRes> getAllTrackingEntries(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return timeTrackingMapper.toTimeRes(user.getTimeTrackings().stream().toList());
    }

    public TaskRes createTask(TaskReq taskReq) {
        Task task = taskMapper.toTask(taskReq);
        User assignee = userRepository.findById(taskReq.getAssignedToId()).orElseThrow();
        User creator = userRepository.findById(taskReq.getCreatedById()).orElseThrow();
        Project project = projectRepository.findById(taskReq.getProjectId()).orElseThrow();
        Task parentTask = taskReq.getParentTaskId() != null ? taskRepository.findById(taskReq.getParentTaskId()).orElseThrow() : null;
        task.setAssignedTo(assignee);
        task.setCreatedBy(creator);
        task.setProject(project);
        task.setParentTask(parentTask);
        task = taskRepository.save(task);
        return taskMapper.toTaskRes(task);
    }
}
