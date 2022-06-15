package service;

import config.DBManager;
import dao.TaskDao;
import dao.jdbc.TaskDaoJDBC;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ApplicationService {
    DBManager dbManager;
    DataSource dataSource;
    public TaskDao taskDao;

    private static ApplicationService instance = null;

    private void setupDAO() {
        taskDao = new TaskDaoJDBC(dataSource);
    }

    private ApplicationService() {
        dbManager = new DBManager();

        try {
            dataSource = dbManager.run();
            setupDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }
}
