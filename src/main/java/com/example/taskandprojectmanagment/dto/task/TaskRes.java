package com.example.taskandprojectmanagment.dto.task;

public class TaskRes extends TaskReq {
    private Integer id;
    private String assignedToFirstName;
    private String assignedToLastName;
    private String createdByFirstName;
    private String createdByLastName;
    private String projectName;
    private String parentTaskTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String createdByFirstName) {
        this.createdByFirstName = createdByFirstName;
    }

    public String getCreatedByLastName() {
        return createdByLastName;
    }

    public void setCreatedByLastName(String createdByLastName) {
        this.createdByLastName = createdByLastName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getParentTaskTitle() {
        return parentTaskTitle;
    }

    public void setParentTaskTitle(String parentTaskTitle) {
        this.parentTaskTitle = parentTaskTitle;
    }
}
