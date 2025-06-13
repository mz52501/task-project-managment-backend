package com.example.taskandprojectmanagment.dto.task;

public class TaskCreatorDTO extends TaskDTO {
    private String createdByFirstName;
    private String createdByLastName;

    public String getCreatedByLastName() {
        return createdByLastName;
    }

    public void setCreatedByLastName(String createdByLastName) {
        this.createdByLastName = createdByLastName;
    }

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String createdByFirstName) {
        this.createdByFirstName = createdByFirstName;
    }
}
