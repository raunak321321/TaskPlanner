package org.example;

import io.dropwizard.views.View;
import org.example.views.NotFoundView;
import javax.ws.rs.*;
//import java.ws.rs.GET;
//import java.ws.rs.Path;
//import java.ws.rs.PathParam;
//import java.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//
@Path("/")
public class FallbackResource {

    @GET
    @Path("{path: .*}")
    @Produces(MediaType.TEXT_HTML)
    public NotFoundView catchAll(@PathParam("path") String path) {
        return new NotFoundView(path);
    }
}