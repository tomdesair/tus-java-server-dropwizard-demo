package me.desair.dropwizard.resources;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import me.desair.dropwizard.api.UploadUtils;
import me.desair.dropwizard.views.IndexView;
import me.desair.dropwizard.views.SubmissionCompletedView;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    private TusFileUploadService tusFileUploadService;

    public IndexResource(TusFileUploadService tusFileUploadService) {
        this.tusFileUploadService = tusFileUploadService;
    }

    @GET
    public IndexView getIndex() {
        return new IndexView();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public SubmissionCompletedView confirmUpload(@FormDataParam("creator") String creator,
                                                 @FormDataParam("description") String description,
                                                 @FormDataParam("uploadUrls") String uploadUrls,
                                                 @Context SecurityContext securityContext) throws IOException, TusException {

        List<String> uploads = new LinkedList<>();
        for (String url : StringUtils.split(StringUtils.trimToEmpty(uploadUrls), ";")) {
            if (StringUtils.isNotBlank(url)) {
                String ownerKey = UploadUtils.getOwnerKey(securityContext);

                //Do something with the bytes uploaded by the user
                //InputStream uploadedBytes = tusFileUploadService.getUploadedBytes(url, ownerKey)

                //Inform user of files we received
                UploadInfo uploadInfo = tusFileUploadService.getUploadInfo(url, ownerKey);
                if(uploadInfo != null) {
                    uploads.add(
                            uploadInfo.getFileName()
                                    + " (" + uploadInfo.getFileMimeType() + "): "
                                    + FileUtils.byteCountToDisplaySize(uploadInfo.getLength())
                    );
                }

                //Since we're done with processing the upload, we can safely remove it now
                tusFileUploadService.deleteUpload(url, ownerKey);
            }
        }

        return new SubmissionCompletedView(creator, description, uploads);
    }

}
