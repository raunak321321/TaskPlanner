package org.example.views;
import org.example.Task;
import io.dropwizard.views.View;

public class UpdateTaskView extends View {
    private final Task task;
    private final String message;

    public UpdateTaskView(Task task, String message) {
        super("updateTask.mustache");
        this.task = task;
        this.message = message;
    }

    public Task getTask() {
        return task;
    }
    public String getMessage(){
        return message;
    }
    public boolean isSuccess() {
        return "success".equals(message);
    }
}
