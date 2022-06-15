package config;

import model.Task;
import model.util.TaskType;
import service.ApplicationService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Date;
import java.util.List;

@WebListener
public class Initializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationService applicationService = ApplicationService.getInstance();
        List<Task> tasks = applicationService.taskDao.getAll();

        if (tasks.size() != 0) return;

        Task work = new Task(1, TaskType.WORK, "Renew schedule", Date.valueOf("2022-06-16"), 1);
        Task home = new Task(1, TaskType.HOME, "Do laundry", Date.valueOf("2022-06-18"), 1);
        Task hobby = new Task(1, TaskType.HOBBY, "Walk in park", Date.valueOf("2022-06-19"), 1);

        applicationService.taskDao.add(work);
        applicationService.taskDao.add(home);
        applicationService.taskDao.add(hobby);
    }
}
