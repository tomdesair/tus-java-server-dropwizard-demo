# tus-java-server Dropwizard Demo
Small Java web server demo based on [Dropwizard](https://www.dropwizard.io) that uses the [tus-java-server library](https://github.com/tomdesair/tus-java-server/) and the [Uppy file uploader](https://uppy.io/) to support the [tus file upload](https://tus.io/) protocol. This allows you to support resumable and asynchronous file uploads in a Dropwizard web application for very large files over slow and unreliable internet connections.

## Build and run
To build and run this demo, execute the following commands:

```
$ git clone https://github.com/tomdesair/tus-java-server-dropwizard-demo.git
$ cd tus-java-server-dropwizard-demo
$ mvn clean package
$ java -jar target/dropwizard-tus-demo-0.0.1-SNAPSHOT.jar server
```

Then visit http://localhost:8080/ in your browser and try to upload a file using the Uppy file uploader.

## CLI Commands
This Dropwizard demo also supports the following CLI commands:

* `java -jar target/dropwizard-tus-demo-0.0.1-SNAPSHOT.jar clean-uploads`: Clean up any expired uploads from the storage backend.
* `java -jar target/dropwizard-tus-demo-0.0.1-SNAPSHOT.jar upload -f "path-to-file"`: Upload the specified file to the server using the `tus-java-client` library. This command is mainly for testing purposes.

## Most Important Classes
To help you in understanding this code, this are the most important classes and files:

* `TusUploadResource`: This class makes the `/api/upload` endpoint available and passes all requests to the `TusFileUploadService` from the [tus-java-server library](https://github.com/tomdesair/tus-java-server/).
* `TusFileUploadApplication`: This class instantiates all endpoints and commands and builds the `TusFileUploadService` based on the given configuration.
* `assets\js\uppy-fileupload.js`: This file contains the configuration of the JavaScript tus client [Uppy](https://uppy.io).
* `IndexResource`: This class will load the main form and processes the submitted information and uploaded files.
* `TusEndpointHealthCheck`: Dropwizard health check class that does a basic check to see if the tus upload endpoint is functioning properly.
* `me.desair.dropwizard.cli`: Package that contains the classes that implement the above mentioned CLI commands.