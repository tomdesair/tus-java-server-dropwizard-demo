package me.desair.dropwizard;

import static me.desair.dropwizard.resources.TusUploadResource.UPLOAD_PATH;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import me.desair.dropwizard.cli.UploadCommand;
import me.desair.dropwizard.resources.IndexResource;
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
        bootstrap.addBundle(new ViewBundle<TusFileUploadConfiguration>());
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addCommand(new UploadCommand());
    }

    @Override
    public void run(final TusFileUploadConfiguration configuration,
                    final Environment environment) {
        //Create and configure the Tus file upload service
        tusFileUploadService = buildTusUploadService(configuration);

        //Resource responsible for processing the tus file uploads
        final TusUploadResource uploadResource = new TusUploadResource(tusFileUploadService);
        environment.jersey().register(uploadResource);

        //Resource responsible for displaying the home page with the Uppy file upload form
        final IndexResource indexResource = new IndexResource();
        environment.jersey().register(indexResource);
    }

    private TusFileUploadService buildTusUploadService(TusFileUploadConfiguration configuration) {
        return new TusFileUploadService()
                .withStoragePath(configuration.getTusDataPath())
                .withDownloadFeature()
                .withUploadURI(UPLOAD_PATH)
                .withThreadLocalCache(true);
    }

}
