package me.desair.dropwizard.cli;

import static me.desair.dropwizard.resources.TusUploadResource.UPLOAD_PATH;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import io.tus.java.client.ProtocolException;
import io.tus.java.client.TusClient;
import io.tus.java.client.TusExecutor;
import io.tus.java.client.TusURLMemoryStore;
import io.tus.java.client.TusUpload;
import io.tus.java.client.TusUploader;
import me.desair.dropwizard.TusFileUploadConfiguration;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadCommand extends ConfiguredCommand<TusFileUploadConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(UploadCommand.class);

    public UploadCommand() {
        super("upload", "Upload a file using the tus protocol to this server");
    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);
        subparser.addArgument("-f", "--file")
                .dest("upload-file")
                .type(String.class)
                .required(true)
                .help("Path to file that has to be uploaded");
    }

    @Override
    protected void run(Bootstrap<TusFileUploadConfiguration> bootstrap,
                       Namespace namespace,
                       TusFileUploadConfiguration configuration) throws Exception {

        final String filePath = namespace.getString("upload-file");

        // Create a new TusClient instance
        final TusClient client = new TusClient();

        // Configure tus HTTP endpoint. This URL will be used for creating new uploads
        // using the Creation extension
        client.setUploadCreationURL(new URL("http://localhost:8080" + UPLOAD_PATH));

        // Enable resumable uploads by storing the upload URL in memory
        client.enableResuming(new TusURLMemoryStore());

        // Open a file using which we will then create a TusUpload. If you do not have
        // a File object, you can manually construct a TusUpload using an InputStream.
        // See the documentation for more information.
        final File file = new File(filePath);
        final TusUpload upload = new TusUpload(file);

        log.info("Starting upload of file {}...", filePath);

        // We wrap our uploading code in the TusExecutor class which will automatically catch
        // exceptions and issue retries with small delays between them and take fully
        // advantage of tus' resumability to offer more reliability.
        // This step is optional but highly recommended.
        TusExecutor executor = new TusExecutor() {
            @Override
            protected void makeAttempt() throws ProtocolException, IOException {
                // First try to resume an upload. If that's not possible we will create a new
                // upload and get a TusUploader in return. This class is responsible for opening
                // a connection to the remote server and doing the uploading.
                TusUploader uploader = client.resumeOrCreateUpload(upload);

                // Upload the file in chunks of 1KB sizes.
                uploader.setChunkSize(1024);

                // Upload the file as long as data is available. Once the
                // file has been fully uploaded the method will return -1
                do {
                    // Calculate the progress using the total size of the uploading file and
                    // the current offset.
                    long totalBytes = upload.getSize();
                    long bytesUploaded = uploader.getOffset();
                    double progress = (double) bytesUploaded / totalBytes * 100;

                    log.info("Upload {} at {}.", filePath, String.format("%06.2f%%", progress));
                } while (uploader.uploadChunk() > -1);

                // Allow the HTTP connection to be closed and cleaned up
                uploader.finish();

                log.info("Upload of file {} finished.", filePath);
                log.info("Upload of file {} available at: {}", filePath, uploader.getUploadURL().toString());
            }
        };
        executor.makeAttempts();
    }
}
