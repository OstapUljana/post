$(document).ready(function() {
	$('#registration_email').blur(function() {
        if($(this).val() != '') {
            var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
            if(pattern.test($(this).val())){
                $(this).css({'border' : '1px solid #569b44'});
                $('#valid').text('Ok');
            } else {
                $(this).css({'border' : '1px solid #ff0000'});
                $('#valid').text('No');
            }
        } else {
            $(this).css({'border' : '1px solid #ff0000'});
            $('#valid').alert('Email can"t be null');
        }
    });
	$('#registration_conf_pass').blur(function() {
        if($(this).val() != '') {
        	var password = $('#registration_password').val();
            if(password==$("#registration_conf_pass").val()){
                $(this).css({'border' : '1px solid #569b44'});
                $('#valid').text('Ok');
            } else {
                $(this).css({'border' : '1px solid #ff0000'});
                $('#valid').text('No');
            }
        } else {
            $(this).css({'border' : '1px solid #ff0000'});
            
        }
    });
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
                    location.href = '/restwebservice/index1.html';
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
