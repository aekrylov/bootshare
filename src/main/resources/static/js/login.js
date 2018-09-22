/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 1:43 PM
 */

$(function() {

    $('#submitBtn').click(function(e) {

        //todo validate
        var btn = $(this);

        if(!btn.attr('data-hasnext')) {
            return;
        }

        e.preventDefault();

        btn.attr('disabled', true);

        var number = $('#phoneNumber').val();

        btn.text('Sending...');
        requestCode(number, $('#requestCode'))
            .done(function() {
                $('#phoneNumber').attr('readonly', true);

                btn.removeAttr('disabled');
                btn.removeAttr('data-hasnext');
                btn.text('Sign in');

                $('#nextStepForm').show();
                $('#code').focus();
            })
            .fail(function() {
                btn.removeAttr('disabled');
                //todo more verbose
            });
    });

    $('#requestCode').click(function(e) {
        var el = $(this);
        el.attr('disabled', true);

        var number = $('#phoneNumber').val();

        requestCode(number, el);
    });
});

function requestCode(phone, button) {
    button.attr('disabled', true);
    button.text('Sending...');
    return $.post('/auth/request_code', {phone: phone})
        .done(function(data) {
            $('#codeHelp').text('A confirmation code was sent to ' + phone);
            button.text('Code sent!');
            setTimeout(function() {
                //todo tooltip
                button.removeAttr('disabled');
                button.text('Resend code');
            }, 60000)
        })
        .fail(function() {
            button.removeAttr('disabled');
            //todo more verbose
        })
}