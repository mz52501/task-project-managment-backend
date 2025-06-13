package com.example.taskandprojectmanagment.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "priority", length = 20)
    private String priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_stage_id")
    private WorkflowStage workflowStage;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "due_date")
    private Instant dueDate;

    @OneToMany(mappedBy = "task")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TimeTracking> timeTrackings = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    // 👇 This is the list of subtasks
    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
    private Set<Task> subtasks = new LinkedHashSet<>();

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Set<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Set<Task> subtasks) {
        this.subtasks = subtasks;
    }

    public Set<TimeTracking> getTimeTrackings() {
        return timeTrackings;
    }

    public void setTimeTrackings(Set<TimeTracking> timeTrackings) {
        this.timeTrackings = timeTrackings;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public WorkflowStage getWorkflowStage() {
        return workflowStage;
    }

    public void setWorkflowStage(WorkflowStage workflowStage) {
        this.workflowStage = workflowStage;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
