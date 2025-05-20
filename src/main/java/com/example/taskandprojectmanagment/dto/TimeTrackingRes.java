package com.example.taskandprojectmanagment.dto;

import com.example.taskandprojectmanagment.components.TimeTrackingStatus;

import java.time.LocalDateTime;

public class TimeTrackingRes {
    private Integer id;
    private Integer taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TimeTrackingStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public TimeTrackingStatus getStatus() {
        return status;
    }

    public void setStatus(TimeTrackingStatus status) {
        this.status = status;
    }
}
