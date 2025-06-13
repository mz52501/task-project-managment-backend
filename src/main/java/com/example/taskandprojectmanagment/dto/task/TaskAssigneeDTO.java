package com.example.taskandprojectmanagment.dto.task;

public class TaskAssigneeDTO extends TaskDTO {
    private String assignedToFirstName;
    private String assignedToLastName;

    public String getAssignedToFirstName() {
        return assignedToFirstName;
    }

    public void setAssignedToFirstName(String assignedToFirstName) {
        this.assignedToFirstName = assignedToFirstName;
    }

    public String getAssignedToLastName() {
        return assignedToLastName;
    }

    public void setAssignedToLastName(String assignedToLastName) {
        this.assignedToLastName = assignedToLastName;
    }
}
