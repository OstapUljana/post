$(document).ready(function() {
	 var URL = window.location.search;
	    var getRequest=URL.split("?")[1];
	    id=getRequest.split("=")[1];
	authorization(id);
});
	
function authorization(id) {
    $('#newpassword').submit(function(e) {
        e.preventDefault();
        var password = $('#registration_password').val();
        if(password==$("#registration_conf_pass").val()) {
	        $.ajax({
	            type: 'post',
	            url: '/restwebservice/rest/session/newpassword',
	            crossDomain: true,
	            data: {'email': id, 'password': password},
	            response: 'text', // response type
	            error: function (data) {
	                //$('#login_message').html(data.responseText);
	            	alert(data.responseText);
	            },
	            statusCode: {
	                // HTTP 307 - redirect
	                307: function (data) {
	                    document.location.href = data.responseText;
	                }
	            }
	
	        });   
        }
        return false;
    });
}
