$(document).ready(function() {
	sendfeedback();
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
            $('#valid').text("Email can't be null");
        }
    });
});

function sendfeedback() {
    $('#feedback').submit(function(e) {
        e.preventDefault();
        var email = $('#email').val();
        var text = $('#text').val();
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/feedback/addfeedback',
            crossDomain: true,
            data: {'email': email, 'text': text},
            response: 'text', // response type
            error: function (data) {
                alert(data.responseText);
            },
            success: function (data) {
                location.href = '/restwebservice/index.html';
            }
           

        });       
        return false;
    });
}
