package com.example.taskandprojectmanagment.controller;

import com.example.taskandprojectmanagment.dto.*;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.security.UserDetailsImpl;
import com.example.taskandprojectmanagment.service.TaskService;
import jakarta.servlet.ServletRequest;
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

    @PostMapping("/timeTracking/start")
    public ResponseEntity<TimeTrackingRes> startTracking(@RequestBody TimeTrackingStartReq req) {
        return ResponseEntity.ok(taskService.startTracking(req));
    }

    @PostMapping("/timeTracking/stop")
    public ResponseEntity<TimeTrackingRes> stopTracking(@RequestBody TimeTrackingStopReq req) {
        return ResponseEntity.ok(taskService.stopTracking(req));
    }
}
