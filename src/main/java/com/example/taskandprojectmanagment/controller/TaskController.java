package com.example.taskandprojectmanagment.controller;

import com.example.taskandprojectmanagment.dto.*;
import com.example.taskandprojectmanagment.dto.task.TaskAssigneeDTO;
import com.example.taskandprojectmanagment.dto.task.TaskCreatorDTO;
import com.example.taskandprojectmanagment.dto.task.TaskReq;
import com.example.taskandprojectmanagment.dto.task.TaskRes;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.security.UserDetailsImpl;
import com.example.taskandprojectmanagment.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/assignee")
    public ResponseEntity<List<TaskCreatorDTO>> getAllTasksForDeveloper(Authentication authentication) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        return ResponseEntity.ok(taskService.getAllTasksForDeveloper(user.getId()));
    }

    @GetMapping("/tasks/creator")
    public ResponseEntity<List<TaskAssigneeDTO>> getAllTasksForCreator(Authentication authentication) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        return ResponseEntity.ok(taskService.getAllTasksForCreator(user.getId()));
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskRes> createTask(@RequestBody TaskReq taskReq) {
        return ResponseEntity.ok(taskService.createTask(taskReq));
    }

    //Time tracking related

    @PostMapping("/timeTracking")
    public ResponseEntity<TimeTrackingRes> setTrackingEntry(@RequestBody TimeTrackingReq req) {
        return ResponseEntity.ok(taskService.setTrackingEntry(req));
    }

    @GetMapping("/timeTracking")
    public ResponseEntity<List<TimeTrackingRes>> getAllTrackingEntries(Authentication authentication) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        return ResponseEntity.ok(taskService.getAllTrackingEntries(user.getId()));
    }
}
