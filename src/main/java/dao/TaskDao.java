package dao;

import model.Task;

import java.util.List;

public interface TaskDao {
    void add(Task task);
    Task get(Integer id);
    List<Task> getAll();
    void remove(Integer id);
}
