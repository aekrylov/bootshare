$(function() {
    $('#fileInput').fileinput({
        showPreview: false,
        showUpload: false,
        maxFileSize: 2048*1024,
        minFileCount: 1,
        maxFileCount: 1,
        elErrorContainer: '#fileInputErrors'
    })
});