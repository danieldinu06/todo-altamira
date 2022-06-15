package model;

import model.util.TaskType;

import java.sql.Date;

public class Task {
    private Integer id;
    private final TaskType type;
    private final String name;
    private final Date dueDate;
    private final Integer estimate;

    public Task(Integer id, TaskType type, String name, Date dueDate, Integer estimate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.dueDate = dueDate;
        this.estimate = estimate;
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
}
