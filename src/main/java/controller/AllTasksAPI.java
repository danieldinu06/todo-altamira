package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Task;
import model.util.TaskType;
import service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

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

        String reader = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        JsonObject jsonTask = new Gson().fromJson(reader, JsonObject.class);

        String name = jsonTask.get("name").getAsString();
        Date dueDate = Date.valueOf(jsonTask.get("dueDate").getAsString());
        TaskType type = TaskType.valueOf(jsonTask.get("type").getAsString().toUpperCase());

        Integer estimate = 0;
        if (!Objects.equals(jsonTask.get("estimate").getAsString(), ""))
            estimate = Integer.valueOf(jsonTask.get("estimate").getAsString());

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
