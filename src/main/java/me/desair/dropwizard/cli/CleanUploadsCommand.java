package me.desair.dropwizard.cli;

import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import me.desair.dropwizard.TusFileUploadApplication;
import me.desair.dropwizard.TusFileUploadConfiguration;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanUploadsCommand extends ConfiguredCommand<TusFileUploadConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(CleanUploadsCommand.class);

    private TusFileUploadApplication uploadApplication;

    public CleanUploadsCommand(TusFileUploadApplication uploadApplication) {
        super("clean-uploads", "Clean-up any expired uploads from the storage backend");
        this.uploadApplication = uploadApplication;
    }

    @Override
    protected void run(Bootstrap<TusFileUploadConfiguration> bootstrap,
                       Namespace namespace,
                       TusFileUploadConfiguration configuration) throws Exception {
        log.info("Started clean-up of expired uploads...");
        uploadApplication.getTusFileUploadService(configuration).cleanup();
        log.info("Clean-up of expired uploads completed");
    }

}
