package dao.jdbc;

import dao.TaskDao;
import model.Task;
import model.util.TaskType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoJDBC implements TaskDao {
    private final DataSource dataSource;

    public TaskDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(Task task) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO tasks (type, name, due_date, estimate, completed, color, creation, days_left) VALUES (?::TASK_TYPE, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, task.getType().toString());
            statement.setString(2, task.getName());
            statement.setDate(3, task.getDueDate());
            statement.setInt(4, task.getEstimate());
            statement.setBoolean(5, task.isCompleted());
            statement.setString(6, task.getColor());
            statement.setDate(7, task.getCreationDate());
            statement.setInt(8, task.getDaysLeft());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            task.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding Task.");
        }
    }

    @Override
    public void update(Task task) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE tasks SET completed = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, task.isCompleted());
            statement.setInt(2, task.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while updating Task.");
        }
    }

    @Override
    public Task get(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT type, name, due_date, estimate, completed, color, creation, days_left FROM tasks WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;

            TaskType type = TaskType.valueOf(resultSet.getString(1));
            String name = resultSet.getString(2);
            Date dueDate = resultSet.getDate(3);
            Integer estimate = resultSet.getInt(4);
            boolean completed = resultSet.getBoolean(5);
            String color = resultSet.getString(6);
            Date creationDate = resultSet.getDate(7);
            Integer daysLeft = resultSet.getInt(8);

            Task task = new Task(type, name, dueDate, estimate, completed);
            task.setId(id);
            task.setColor(color);
            task.setCreationDate(creationDate);
            task.setDaysLeft(daysLeft);

            return task;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Task.");
        }
    }

    @Override
    public List<Task> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, type, name, due_date, estimate, completed, color, creation, days_left FROM tasks ORDER BY creation";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Task> result = new ArrayList<>();
            while(resultSet.next()) {
                Integer id = resultSet.getInt(1);
                TaskType type = TaskType.valueOf(resultSet.getString(2));
                String name = resultSet.getString(3);
                Date dueDate = resultSet.getDate(4);
                Integer estimate = resultSet.getInt(5);
                boolean completed = resultSet.getBoolean(6);
                String color = resultSet.getString(7);
                Date creationDate = resultSet.getDate(8);
                Integer daysLeft = resultSet.getInt(9);

                Task task = new Task(type, name, dueDate, estimate, completed);
                task.setId(id);
                task.setColor(color);
                task.setCreationDate(creationDate);
                task.setDaysLeft(daysLeft);

                result.add(task);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all Tasks.");
        }
    }

    @Override
    public void remove(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while removing Task.");
        }
    }

}
