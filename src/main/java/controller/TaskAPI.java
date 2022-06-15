package controller;

import com.google.gson.Gson;
import model.Task;
import service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/task/*"})
public class TaskAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getPathInfo().substring(1));

        ApplicationService applicationService = ApplicationService.getInstance();
        Task task = applicationService.taskDao.get(id);

        String json = new Gson().toJson(task);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getPathInfo().substring(1));

        ApplicationService applicationService = ApplicationService.getInstance();
        Task task = applicationService.taskDao.get(id);
        task.setCompleted(true);

        applicationService.taskDao.update(task);
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getPathInfo().substring(1));

        ApplicationService applicationService = ApplicationService.getInstance();
        applicationService.taskDao.remove(id);

        doGet(request, response);
    }
}
