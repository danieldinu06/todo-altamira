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

        Task work = new Task(TaskType.WORK, "Renew schedule", Date.valueOf("2022-06-16"), 1, false);
        Task home = new Task(TaskType.HOME, "Do laundry", Date.valueOf("2022-06-18"), 1, false);
        Task hobby = new Task(TaskType.HOBBY, "Walk in park", Date.valueOf("2022-06-19"), 1, false);

        work.setId(1);
        home.setId(2);
        hobby.setId(3);

        applicationService.taskDao.add(work);
        applicationService.taskDao.add(home);
        applicationService.taskDao.add(hobby);
    }
}
