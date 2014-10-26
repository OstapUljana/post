$(document).ready(function() {
    $('#regist_field').submit(function(e) {
        e.preventDefault();
        var email = $('#registration_email').val();
        var password = $('#registration_password').val();
        var name = $("#registration_name").val();
        if(password==$("#registration_conf_pass").val()) {
            $.ajax({
                type: 'post',
                url: '/restwebservice/rest/reg',
                crossDomain: true,
                data: {'email': email, 'name': name, 'password': password},
                response: 'text', // response type
                success: function (data) {
                    location.href = '/restwebservice/index.html';
                },
                statusCode: {
                	// HTTP 409 - Conflict
                	409: function (data) {
                		$('#status').html(data.responseText);	
                	}
                }

            });

        } else {
            alert("Не правильні паролі");
        }
        return false;
    });

});
