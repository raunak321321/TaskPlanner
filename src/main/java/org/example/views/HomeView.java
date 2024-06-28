package org.example.views;
import org.example.Task;
import io.dropwizard.views.View;
import java.util.List;

public class HomeView extends View {
    private final List<Task> tasks;

    public HomeView(List<Task> tasks) {
        super("home.mustache");
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
