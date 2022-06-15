package model;

import model.util.TaskType;

import java.sql.Date;

public class Task {
    private Integer id;
    private final TaskType type;
    private final String name;
    private final Date dueDate;
    private final Integer estimate;
    private boolean completed;

    public Task(TaskType type, String name, Date dueDate, Integer estimate, boolean completed) {
        this.type = type;
        this.name = name;
        this.dueDate = dueDate;
        this.estimate = estimate;
        this.completed = completed;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public TaskType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
