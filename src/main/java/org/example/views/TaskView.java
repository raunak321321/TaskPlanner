package org.example.views;
import org.example.Task;
import io.dropwizard.views.View;
import java.util.List;

public class TaskView extends View {
    private final Task task;
    private final String message;

    public TaskView(Task task, String message) {
        super("task.mustache");
        this.task = task;
        this.message = message;
    }

    public Task getTask() {
        return task;
    }

    public String getMessage() {
        return message;
    }
    public boolean isSuccess() {
        return "success".equals(message);
    }
}
