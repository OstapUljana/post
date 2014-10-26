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

    $('#navbarHeader').ready(function () {
        // If user is Sign In show logout
        $.ajax({
            type: 'get',
            url: '/restwebservice/rest/session/get-user',
            crossDomain: true,
            success: function (data) {
                if (data.name == null) { // if response doesn't have user
                    loginButtonEnable();
                } else {
                    logoutButtonEnable(data.name);
                }
            }
        });

    });

    function logoutButtonEnable(name) {
        $('#navbarHeader').html(
                "<a href='javascript:void(0)' id = 'logout_button'>Вийти</a></li>"+
                "<div id='hellouser'>Доброго дня, " + name + "</div>"
        );

        // set on click action on logout button
        $('#logout_button').click(function () {
            $.ajax({
                type: 'post',
                url: '/restwebservice/rest/session/logout',
                crossDomain: true,
                success: function (data) {
                    location.reload();
                }
            });
        });
    }

    function loginButtonEnable() {
        $(('#navbarHeader')).html(
                "<ul class='nav navbar-nav pull-right'>" +
                "<li class='active'><a href='/'>Головна</a></li>" +
                "<li><a href='/allbooks.html'>Усі книжки</a></li>" +
                "<li><a href='/how2buy.html'>Як придбати</a></li>" +
                "<li><a href='/about.html'>Про видавництво</a></li>" +
                "<li><a href='javascript:void(0)' id = 'enter'>Увійти</a></li>" +
                "</ul>"
        );

        $("#enter").click(function () {
            $("#login").slideToggle("slow");
            $(this).toggleClass("active");
        });
    }
  
          