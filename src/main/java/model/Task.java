package model;

import model.util.TaskType;

import java.sql.Date;

public class Task {
    private Integer id;
    private final TaskType type;
    private String color;
    private final String name;
    private final Date dueDate;
    private final Integer estimate;
    private boolean completed;
    private Date creationDate;
    private Integer daysLeft;

    public Task(TaskType type, String name, Date dueDate, Integer estimate, boolean completed) {
        this.type = type;
        this.name = name;
        this.dueDate = dueDate;
        this.estimate = estimate;
        this.completed = completed;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(Integer daysLeft) {
        this.daysLeft = daysLeft;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
