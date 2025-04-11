package com.example.taskandprojectmanagment.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "workflow_stages")
public class WorkflowStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id")
    private TaskWorkflow workflow;

    @Column(name = "stage_name", nullable = false, length = 50)
    private String stageName;

    @Column(name = "\"position\"", nullable = false)
    private Integer position;

    @OneToMany(mappedBy = "workflowStage")
    private Set<Task> tasks = new LinkedHashSet<>();

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public TaskWorkflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(TaskWorkflow workflow) {
        this.workflow = workflow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
