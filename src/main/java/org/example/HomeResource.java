package org.example;

import javax.ws.rs.*;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.Task;
import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import org.example.TaskDAO;
import javax.ws.rs.core.MultivaluedMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.example.views.HomeView;
import org.example.views.NotFoundView;
import org.example.views.UpdateTaskView;
import org.example.views.ResultView;
import org.example.views.TaskView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/home")
public class HomeResource {
//    private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);
//    @GET
//    public HomeView getHomePage() {
//        LOGGER.info("Welcome to the home page!");
//        return new HomeView("Welcome to the Home Page!");
//    }
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);
    private final TaskDAO taskDAO;

    public HomeResource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GET
    @UnitOfWork
    public HomeView getAllTasks() {
        List<Task> tasks = taskDAO.findAll();
        LOGGER.info("Retrieved tasks: " + tasks);
        return new HomeView(tasks);
    }

    @GET
    @Path("/task/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public TaskView getTaskById(@PathParam("id") Long id) {
        Task task = taskDAO.findById(id);
        if (task == null) {
            return new TaskView(task,"fail");
        }
//        return Response.ok(task).build();
        return new TaskView(task,"success");
    }

    @POST
    @Path("/task")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public ResultView createTask(MultivaluedMap<String, String> formParams) {
//        System.out.println(formParams.getFirst("description"));
//        System.out.println("Raunak Here/n/n/n/n/n");
//        System.out.println(formParams.getFirst("startDate"));
        String startDateStr = formParams.getFirst("startDate");
        String targetDateStr = formParams.getFirst("targetDate");
        String description = formParams.getFirst("description");
        String status = "TODO";
        String heading = formParams.getFirst("heading");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate;
        Date targetDate;
        try {
            startDate = sdf.parse(startDateStr);
            targetDate = sdf.parse(targetDateStr);
        } catch (ParseException e) {
            return new ResultView("There was an error creating your task. Please try again.","Task Creation Failed");
        }

        Task newTask = new Task(startDate, targetDate, description, status, heading);
        Task createdTask = taskDAO.create(newTask);

        return new ResultView("Your task has been successfully added.","Task Created Successfully!");
    }


    @POST
    @Path("/task/update2/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public ResultView updateTask(MultivaluedMap<String, String> formParams, @PathParam("id") Long id) {
//        System.out.println(formParams.getFirst("description"));
//        System.out.println("Raunak Here/n/n/n/n/n");
//        System.out.println(formParams.getFirst("startDate"));
        String startDateStr = formParams.getFirst("startDate");
        String targetDateStr = formParams.getFirst("targetDate");
        String description = formParams.getFirst("description");
        String status = formParams.getFirst("status");
        String heading = formParams.getFirst("heading");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate;
        Date targetDate;
        try {
            startDate = sdf.parse(startDateStr);
            targetDate = sdf.parse(targetDateStr);
        } catch (ParseException e) {
            return new ResultView("There was an error updating your task. Please try again.","Task Updation Failed");
        }

        Task newTask = new Task(startDate, targetDate, description, status, heading);
        Task task = taskDAO.findById(id);
        taskDAO.delete(task);
        Task createdTask = taskDAO.create(newTask);

        return new ResultView("Your task has been successfully updated.","Task Updated Successfully!");
    }

    @GET
    @Path("/task/delete/{id}")
    @UnitOfWork
    public ResultView deleteTask(@PathParam("id") Long id) {
        Task task = taskDAO.findById(id);
        if (task == null) {
            LOGGER.warn("Task with ID {} not found for deletion.", id);
            return new ResultView("Task Deletion Failed", "Task not found.");
        }

        taskDAO.delete(task);
        String successMessage = "Task with ID " + id +  " deleted.";
        return new ResultView(successMessage,"Task Deleted Successfully!");
    }

    @GET
    @Path("/task/update/{id}")
    @UnitOfWork
    public UpdateTaskView updateFormTask(@PathParam("id") Long id) {
        Task task = taskDAO.findById(id);
        if (task == null) {
            LOGGER.warn("Task with ID {} not found for updation.", id);
            return new UpdateTaskView(task,"fail");
        }

        return new UpdateTaskView(task,"success");
    }

    @GET
    @Path("{path: .*}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public NotFoundView catchAllHome(@PathParam("path") String path) {
        LOGGER.warn("Attempted to access non-existent path under /home: {}", path);
        return new NotFoundView("/home/" + path);
    }
}
