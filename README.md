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
TODO

## Most Important Classes
TODO
