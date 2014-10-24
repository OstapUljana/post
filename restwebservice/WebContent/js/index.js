    $(document).ready(function () {      
        addAuthorization();
    });

   
    function addAuthorization() {
        $('#login').submit(function () {
            var email = $('#email').val();
            var password = $('#password').val();

            $.ajax({
                type: 'post',
                url: '/rest/log',
                crossDomain: true,
                data: {'email': email, 'password': password},
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

  

    