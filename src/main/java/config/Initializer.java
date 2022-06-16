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

        Task work = new Task(TaskType.WORK, "Finish project", Date.valueOf("2022-06-30"), 6, false);
        Task home = new Task(TaskType.HOME, "Renovate kitchen", Date.valueOf("2022-06-14"), 2, true);
        Task hobby = new Task(TaskType.HOBBY, "Walk in park", Date.valueOf("2022-06-17"), 1, false);

        work.setId(1);
        work.setColor("purple");
        work.setCreationDate(Date.valueOf("2022-06-19"));

        int daysLeft = (int)(work.getDueDate().getTime() - new Date(System.currentTimeMillis()).getTime()) / (1000 * 60 * 60 * 24) + 1;

        work.setDaysLeft(daysLeft);

        home.setId(2);
        home.setColor("blue");
        home.setCreationDate(Date.valueOf("2022-06-10"));

        daysLeft = (int)(home.getDueDate().getTime() - new Date(System.currentTimeMillis()).getTime()) / (1000 * 60 * 60 * 24) + 1;

        home.setDaysLeft(daysLeft);

        hobby.setId(3);
        hobby.setColor("green");
        hobby.setCreationDate(new Date(System.currentTimeMillis()));

        daysLeft = (int)(hobby.getDueDate().getTime() - new Date(System.currentTimeMillis()).getTime()) / (1000 * 60 * 60 * 24) + 1;

        hobby.setDaysLeft(daysLeft);

        applicationService.taskDao.add(work);
        applicationService.taskDao.add(home);
        applicationService.taskDao.add(hobby);
    }
}
