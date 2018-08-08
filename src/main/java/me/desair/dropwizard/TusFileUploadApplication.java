package me.desair.dropwizard;

import static me.desair.dropwizard.resources.TusUploadResource.UPLOAD_PATH;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import me.desair.dropwizard.cli.CleanUploadsCommand;
import me.desair.dropwizard.cli.UploadCommand;
import me.desair.dropwizard.resources.IndexResource;
import me.desair.dropwizard.resources.TusUploadResource;
import me.desair.tus.server.TusFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TusFileUploadApplication extends Application<TusFileUploadConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(TusFileUploadApplication.class);

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
        bootstrap.addCommand(new CleanUploadsCommand(this));
    }

    @Override
    public void run(final TusFileUploadConfiguration configuration,
                    final Environment environment) {

        //Resource responsible for processing the tus file uploads
        final TusUploadResource uploadResource = new TusUploadResource(getTusFileUploadService(configuration));
        environment.jersey().register(uploadResource);

        //Resource responsible for displaying the home page with the Uppy file upload form
        final IndexResource indexResource = new IndexResource();
        environment.jersey().register(indexResource);
    }

    public TusFileUploadService getTusFileUploadService(TusFileUploadConfiguration configuration) {
        if(tusFileUploadService == null) {
            //Create and configure the Tus file upload service
            String tusDataPath = configuration.getTusDataPath();

            log.info("Tus file upload data is stored in directory {}", tusDataPath);

            tusFileUploadService = new TusFileUploadService()
                    .withStoragePath(tusDataPath)
                    .withUploadExpirationPeriod(configuration.getUploadExpirationInMilliseconds())
                    .withDownloadFeature()
                    .withUploadURI(UPLOAD_PATH)
                    .withThreadLocalCache(true);
        }

        return tusFileUploadService;
    }

}
