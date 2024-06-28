package org.example.views;

import io.dropwizard.views.View;

public class ResultView extends View {
    private final String message;
    private final String title;
    public ResultView(String message, String title) {
        super("result.mustache");
        this.message = message;
        this.title = title;
    }

    public String getMessage(){
        return message;
    }
    public String getTitle(){
        return title;
    }
}
