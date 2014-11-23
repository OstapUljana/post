$('#bs-example-navbar-collapse-1').ready(function () {
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
    $('#bs-example-navbar-collapse-1').html(
            "<ul class='nav navbar-nav navbar-right'>"+
                "<li> <a href='#'>About</a> </li>"+
                "<li> <a href='#'>Feedback</a> </li>"+
                "<li> <a href='javascript:void(0)' id = 'logout_button'>Log out</a></li>"+
                "<li> <a href='#'>" + name +"</a></li>"+
            "</ul>");

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
    $(('#bs-example-navbar-collapse-1')).html(
    		"<ul class='nav navbar-nav navbar-right'>"+
            "<li> <a href='#'>About</a> </li>"+
            "<li> <a href='#'>Feedback</a> </li>"+
            "<li> <a href='login.html'>Log in</a></li>"+
        "</ul>");
    
    $("#enter").click(function () {
        $("#login").slideToggle("slow");
        $(this).toggleClass("active");
    });
}

function allPost(data, clas) {
    var i = 0;
    if (data != null) {
        $("." + clas).each(function () {
            $(this).html(
            		 "<div class='content-section-a'><div class='container'>"+
     		         "<div class='row' id='row'>" + 
            		"<hr class='section-heading-spacer'>"+
                    "<div class='clearfix'></div>"+
                    "<a href='rest/article/getPagePost/"+data[i].idArticle+"'><h2 class='section-heading'>" + data[i].title+ "</h2></a>"+
                    "<p class='lead'>"+ data[i].text+ "</p>"+
                    "</div></div></div>");
            i++
        });
    }
}
    

    $('.pack').ready(function () {
        $.ajax({
            type: "get",
            url: "/restwebservice/rest/article/list/3/0/datetime",
            crossDomain: true,
            dataType: "json",
            cache: false,
            success: function (data) {
            	allPost(data, "pack2");
            }
        });
        
    });
    
    
    $(document).ready(function() {
    	function checkEmail() {
    	var email = document.getElementById('emailaddress');
    	var filter = /^(([^<>()[]\.,;:s@"]+(.[^<>()[]\.,;:s@"]+)*)|(".+"))@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}])|(([a-zA-Z-0-9]+.)+[a-zA-Z]{2,}))$/;
    	if (!filter.test(email.value)) {
    	alert('Please provide a valid email address');
    	email.focus;
    	return false;
    	}
    	}
        /*$('#email').blur(function() {
            if($(this).val() != '') {
                var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
                if(pattern.test($(this).val())){
                    $(this).css({'border' : '1px solid #569b44'});
                    $('#valid').text('Верно');
                } else {
                    $(this).css({'border' : '1px solid #ff0000'});
                    $('#valid').text('Не верно');
                }
            } else {
                $(this).css({'border' : '1px solid #ff0000'});
                $('#valid').text('Поле email не должно быть пустым');
            }
        });*/
    });


