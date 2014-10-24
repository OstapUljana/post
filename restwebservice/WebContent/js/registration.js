$(document).ready(function() {
$("#registration_email").change(function(){
    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);

    if (!pattern.test($(this).val())) {
        $(this).css({ border:"2px solid red"});
        $(".email-fail").css({display:"block"});
    }
    else{
        $(this).css({ border:"1px solid #ccc"});
        $(".email-fail").css({display:"none"});
    }
});

    $("#registration_password").change(function(){

        if (($(this).val()!=$("#registration_conf_pass").val())) {
            $(this).css({ border:"2px solid red"});
            $("#registration_conf_pass").css({border:"2px solid red"});
            $(".password-fail").css({display:"block"});
        }
        else{
            $(this).css({ border:"1px solid #ccc"});
            $("#registration_conf_pass").css({ border:"1px solid #ccc"});
            $(".password-fail").css({display:"none"});
        }
    });
    $("#registration_conf_pass").change(function(){

        if (($(this).val()!=$("#registration_password").val())) {
            $(this).css({ border:"2px solid red"});
            $("#registration_conf_pass").css({border:"2px solid red"});
            $(".password-fail").css({display:"block"});
        }
        else{
            $(this).css({ border:"1px solid #ccc"});
            $("#registration_password").css({ border:"1px solid #ccc"});
            $(".password-fail").css({display:"none"});
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
                url: 'restwebservice/rest/reg',
                crossDomain: true,
                data: {'email': email, 'name': name, 'password': password},
                response: 'text', // response type
                success: function (data) {
                    location.href = '/thnks.html';
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
