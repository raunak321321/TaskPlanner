package org.example.views;

import io.dropwizard.views.View;

public class NotFoundView extends View {
    private final String path;
    public NotFoundView(String path) {
        super("notFound.mustache");
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
