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
            String sql = "INSERT INTO tasks (type, name, due_date, estimate) VALUES (?::TASK_TYPE, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, task.getType().toString());
            statement.setString(2, task.getName());
            statement.setDate(3, task.getDueDate());
            statement.setInt(4, task.getEstimate());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            task.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task get(Integer id) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, type, name, due_date, estimate FROM tasks";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Task> result = new ArrayList<>();
            while(resultSet.next()) {
                Integer id = resultSet.getInt(1);
                TaskType type = TaskType.valueOf(resultSet.getString(2));
                String name = resultSet.getString(3);
                Date dueDate = resultSet.getDate(4);
                Integer estimate = resultSet.getInt(5);

                Task task = new Task(id, type, name, dueDate, estimate);
                result.add(task);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

}
