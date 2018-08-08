package me.desair.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.desair.dropwizard.views.IndexView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    @GET
    public IndexView getIndex() {
        return new IndexView();
    }

}
