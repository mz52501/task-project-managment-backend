package com.example.taskandprojectmanagment.service;

import com.example.taskandprojectmanagment.dto.*;
import com.example.taskandprojectmanagment.mapper.TaskMapper;
import com.example.taskandprojectmanagment.mapper.TimeTrackingMapper;
import com.example.taskandprojectmanagment.model.Task;
import com.example.taskandprojectmanagment.model.TimeTracking;
import com.example.taskandprojectmanagment.model.User;
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

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper, TimeTrackingRepository timeTrackingRepository, TimeTrackingMapper timeTrackingMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
        this.timeTrackingRepository = timeTrackingRepository;
        this.timeTrackingMapper = timeTrackingMapper;
    }

    public List<TaskCreatorDTO> getAllTasksForDeveloper(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return taskMapper.toTaskCreatorDTOs(user.getTasks().stream().toList());
    }

    public List<TaskAssigneeDTO> getAllTasksForCreator(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return taskMapper.toTaskAssigneeDTOs(user.getCreatedTasks().stream().toList());
    }

    public TimeTrackingRes startTracking(TimeTrackingStartReq req) {
        TimeTracking timeTracking = timeTrackingMapper.toTimeStart(req);
        Task task = taskRepository.findById(req.getTaskId())
            .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        timeTracking.setTask(task);
        timeTracking = timeTrackingRepository.save(timeTracking);
        return timeTrackingMapper.toTimeRes(timeTracking);
    }

    public TimeTrackingRes stopTracking(TimeTrackingStopReq req) {
        TimeTracking timeTracking = timeTrackingRepository.findById(req.getId())
            .orElseThrow(() -> new EntityNotFoundException("Time tracking not found"));
        timeTrackingMapper.updateTimeFromStartReq(req, timeTracking);
        timeTracking = timeTrackingRepository.save(timeTracking);
        return timeTrackingMapper.toTimeRes(timeTracking);
    }
}
