package controller;

import com.google.gson.Gson;
import model.Task;
import model.util.TaskType;
import service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/tasks"})
public class AllTasksAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationService applicationService = ApplicationService.getInstance();
        List<Task> tasks = applicationService.taskDao.getAll();

        String json = new Gson().toJson(tasks);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationService applicationService = ApplicationService.getInstance();

        String name = request.getParameter("name");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        Integer estimate = Integer.valueOf(request.getParameter("estimate"));
        TaskType type = TaskType.valueOf(request.getParameter("type").toUpperCase());

        String color = "purple";
        if (type == TaskType.HOME) {
            color = "blue";
        } else if (type == TaskType.HOBBY) {
            color = "green";
        }

        int daysLeft = (int)(dueDate.getTime() - new Date(System.currentTimeMillis()).getTime()) / (1000 * 60 * 60 * 24) + 1;

        Task task = new Task(type, name, dueDate, estimate, false);
        task.setColor(color);
        task.setCreationDate(new Date(System.currentTimeMillis()));
        task.setDaysLeft(daysLeft);

        applicationService.taskDao.add(task);

        response.sendRedirect("/");
    }
}
