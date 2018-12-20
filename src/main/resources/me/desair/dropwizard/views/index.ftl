<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tus File Upload in Dropwizard</title>
        <link href="https://transloadit.edgly.net/releases/uppy/v0.28.0/dist/uppy.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Tus File Upload in Dropwizard</h1>
        <h3>Files Submission Form</h3>
        <form action="/" method="post" enctype="multipart/form-data">
            <p>
                Creator: <input type="text" name="creator" />
            </p>
            <p>
                Description: <input type="text" name="description" />
            </p>
            <p>
                <div class="DashboardContainer"></div>
            </p>
            <input id="uploadUrls" type="hidden" name="uploadUrls" value="">
            <p>
                <strong>Make sure that all files are uploaded before clicking "Submit"!</strong>
            </p>
            <p>
                <i>(DEV INFO: as an alternative, you can also configure Uppy to auto-start uploading files upon selection by setting "autoProceed" to true.)</i>
            </p>
            <input type="submit" value="Submit" />
        </form>

        <script src="https://transloadit.edgly.net/releases/uppy/v0.28.0/dist/uppy.min.js"></script>
        <script src="/js/uppy-fileupload.js"></script>
    </body>
</html>