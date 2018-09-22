/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 1:43 PM
 */

$(function() {

    $('#loginForm').submit(function(e) {
        //validate form
        $(this).addClass('was-validated');
        if(this.checkValidity() === false) {
            e.preventDefault();
            e.stopPropagation();
            return;
        }

        var btn = $('#submitBtn', this);
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
                $('#nextStepForm input').removeAttr('disabled');
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

            var finalDate = Date.now() + 60000;
            button.countdown(finalDate)
                .on('update.countdown', function(e) {
                    $(this).text('Resend in '+e.offset.seconds+'...');
                })
                .on('finish.countdown', function(e) {
                    $(this).removeAttr('disabled');
                    $(this).text('Resend code');
                });
        })
        .fail(function() {
            button.removeAttr('disabled');
            $('#codeHelp').text('Error while sending confirmation code, try again');
        })
}