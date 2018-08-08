package me.desair.dropwizard;

import static me.desair.dropwizard.resources.TusUploadResource.UPLOAD_PATH;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.desair.dropwizard.resources.TusUploadResource;
import me.desair.tus.server.TusFileUploadService;

public class TusFileUploadApplication extends Application<TusFileUploadConfiguration> {

    private TusFileUploadService tusFileUploadService;

    public static void main(final String[] args) throws Exception {
        new TusFileUploadApplication().run(args);
    }

    @Override
    public String getName() {
        return "TusFileUpload";
    }

    @Override
    public void initialize(final Bootstrap<TusFileUploadConfiguration> bootstrap) {
        //Nothing to do here
    }

    @Override
    public void run(final TusFileUploadConfiguration configuration,
                    final Environment environment) {

        tusFileUploadService = new TusFileUploadService()
                .withStoragePath(configuration.getTusDataPath())
                .withDownloadFeature()
                .withUploadURI(UPLOAD_PATH);

        final TusUploadResource uploadResource = new TusUploadResource(tusFileUploadService);
        environment.jersey().register(uploadResource);

    }

}
