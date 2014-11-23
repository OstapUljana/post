$(document).ready(function() {
	forgot();
});
$(document).ready(function() {
    $('#email').blur(function() {
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
});
	
function forgot() {
    $('#forgotpassword').submit(function(e) {
        e.preventDefault();
        var email = $('#email').val();
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/session/forgotpassword',
            crossDomain: true,
            data: {'email': email},
            response: 'text', // response type
            error: function (data) {
                //$('#login_message').html(data.responseText);
            	//alert(data.responseText);
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
