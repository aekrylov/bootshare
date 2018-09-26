$(function() {
    $('#fileInput').fileinput({
        showPreview: false,
        showUpload: false,
        maxFileSize: 2048*1024,
        //minFileCount: 1,
        maxFileCount: 1,
        elErrorContainer: '#fileInputErrors'
    });

    $('form').submit(function(e) {
        var form = $(this);
        form.addClass('was-validated');
        if(this.checkValidity() === false) {
            e.preventDefault();
            e.stopPropagation();
        }
    })
});
