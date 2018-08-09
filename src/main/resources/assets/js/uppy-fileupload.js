var uppy = Uppy.Core({
    autoProceed: false
});

uppy.use(Uppy.Dashboard, {
    inline: true,
    target: '.DashboardContainer',
    replaceTargetContent: true,
    note: 'The best way to test this is with a file of several hundred MB (e.g. a Linux distro DVD image)',
    maxHeight: 450,
    metaFields: [{
            id: 'license',
            name: 'License',
            placeholder: 'specify license'
        },
        {
            id: 'caption',
            name: 'Caption',
            placeholder: 'describe what the image is about'
        }
    ]
});

uppy.use(Uppy.Tus, {
    endpoint: 'http://localhost:8080/api/upload'
});

uppy.on('upload-success', function(file, upload) {
    console.log("Upload " + file.name + " completed with URL " + upload.url);
    document.getElementById('uploadUrls').value += upload.url + ";";
    console.log("Added upload URL " + upload.url + " to submission form data so that it can be processed by the backend");
});
