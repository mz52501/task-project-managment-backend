package com.example.taskandprojectmanagment.dto;

import com.example.taskandprojectmanagment.dto.task.TaskDTO;

import java.util.List;

public class TaskWithTimeTrackingDTO {

    private TaskDTO task;
    private Integer duration;
    private List<TimeTrackingRes> timeTrackings;

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<TimeTrackingRes> getTimeTrackings() {
        return timeTrackings;
    }

    public void setTimeTrackings(List<TimeTrackingRes> timeTrackings) {
        this.timeTrackings = timeTrackings;
    }
}
