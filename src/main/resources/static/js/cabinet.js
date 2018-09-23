$(function() {
    $('.deleteFile').click(function() {
        var button = $(this);
        var id = button.attr('data-id');

        $.post('/cabinet/delete/' + id)
            .done(function() {
                $(button).parents('li').hide();
            })
            .fail(function() {
                alert('Unable to delete file');
            })
    })
})