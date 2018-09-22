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
        $.post('/auth/request_code', {phone: number})
            .done(function(data) {
                $('#phoneNumber').attr('readonly', true);

                btn.removeAttr('disabled');
                btn.removeAttr('data-hasnext');
                btn.text('Sign in');
                $('#nextStepForm').show();

                $('#codeHelp').text('Code was sent successfully to ' + number);

                //disable resend code button
                //todo count down
                $('#requestCode').attr('disabled', true);
                setTimeout(function() {
                    $('#requestCode').removeAttr('disabled');
                    $('#requestCode').text('Resend code');
                }, 60000)
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

        el.text('Sending...');
        $.post('/auth/request_code', {phone: number})
            .done(function(data) {
                $('#codeHelp').text('Code was sent successfully to ' + number);
                el.text('Code sent!');
                setTimeout(function() {
                    //todo tooltip
                    el.removeAttr('disabled');
                    el.text('Resend code');
                }, 60000)
            })
            .fail(function() {
                el.removeAttr('disabled');
                //todo more verbose
            });
    });
});