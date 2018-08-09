package me.desair.dropwizard.resources;

import static me.desair.dropwizard.resources.TusUploadResource.UPLOAD_PATH;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import io.dropwizard.jersey.PATCH;
import me.desair.dropwizard.api.UploadUtils;
import me.desair.tus.server.TusFileUploadService;

@Path(UPLOAD_PATH)
public class TusUploadResource {

    public static final String UPLOAD_PATH = "/api/upload";

    private TusFileUploadService tusFileUploadService;

    public TusUploadResource(TusFileUploadService tusFileUploadService) {
        this.tusFileUploadService = tusFileUploadService;
    }

    @POST
    public void processPost(@Context HttpServletRequest request,
                           @Context HttpServletResponse response,
                           @Context SecurityContext securityContext) throws IOException {
        processRequest(request, response, securityContext);
    }

    @OPTIONS
    public void processOptions(@Context HttpServletRequest request,
                            @Context HttpServletResponse response,
                            @Context SecurityContext securityContext) throws IOException {
        processRequest(request, response, securityContext);
    }

    @GET
    @Path("/{id}")
    public void processGet(@Context HttpServletRequest request,
                           @Context HttpServletResponse response,
                           @Context SecurityContext securityContext) throws IOException {
        processRequest(request, response, securityContext);
    }

    @DELETE
    @Path("/{id}")
    public void processDelete(@Context HttpServletRequest request,
                           @Context HttpServletResponse response,
                           @Context SecurityContext securityContext) throws IOException {
        processRequest(request, response, securityContext);
    }

    @PATCH
    @Path("/{id}")
    public void processPatch(@Context HttpServletRequest request,
                           @Context HttpServletResponse response,
                           @Context SecurityContext securityContext) throws IOException {
        processRequest(request, response, securityContext);
    }

    @HEAD
    @Path("/{id}")
    public void processHead(@Context HttpServletRequest request,
                           @Context HttpServletResponse response,
                           @Context SecurityContext securityContext) throws IOException {
        processRequest(request, response, securityContext);
    }

    @POST
    @Path("/{id}")
    public void processPostOnId(@Context HttpServletRequest request,
                            @Context HttpServletResponse response,
                            @Context SecurityContext securityContext) throws IOException {
        processRequest(request, response, securityContext);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response,
                                SecurityContext securityContext) throws IOException {

        tusFileUploadService.process(request, response, UploadUtils.getOwnerKey(securityContext));
    }

}
