package com.example.taskandprojectmanagment.dto;

import com.example.taskandprojectmanagment.components.TimeTrackingStatus;

import java.time.LocalDateTime;

public class TimeTrackingStopReq {
    private Integer id;
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

    public TimeTrackingStatus getStatus() {
        return status;
    }

    public void setStatus(TimeTrackingStatus status) {
        this.status = status;
    }
}
