package com.example.taskandprojectmanagment.dto;

import com.example.taskandprojectmanagment.components.TimeTrackingStatus;

import java.time.LocalDateTime;

public class TimeTrackingStartReq {
    private TimeTrackingStatus status;
    private Integer taskId;
    private LocalDateTime startTime;

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
