$(document).ready(function() {
	 var URL = window.location.search;
	    var getRequest=URL.split("?")[1];
	    id=getRequest.split("=")[1];
	newPassword(id);
});
	
function newPassword(id) {
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
	            success: function (data) {
	            	location.href = '/restwebservice/index.html';
	            },
	            statusCode: {
	                409: function (data) {
	                	alert(data.responseText);
	                }
	            }	
	        });   
        }
        return false;
    });
}
