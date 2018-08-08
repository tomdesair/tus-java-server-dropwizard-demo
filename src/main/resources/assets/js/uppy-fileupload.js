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