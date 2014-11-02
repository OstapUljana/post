$(document).ready(function() {
	authorization();
});
	
function authorization() {
    $('#login').submit(function(e) {
        e.preventDefault();
        var email = $('#email').val();
        var password = $('#password').val();
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/session/log',
            crossDomain: true,
            data: {'email': email, 'password': password},
            response: 'text', // response type
            error: function (data) {
                $('#login_message').html(data.responseText);
            },
            statusCode: {
                // HTTP 307 - redirect
                307: function (data) {
                    document.location.href = data.responseText;
                }
            }

        });       
        return false;
    });
}
