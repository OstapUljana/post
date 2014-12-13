$(document).ready(function() {
    $('#create_post').submit(function(e) {
        e.preventDefault();
        var post1 = $('#post1').val(); 
        var post = $('#post').val();       
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/article/createpost',
            crossDomain: true,
            data: {'post1': post1, 'post': post},
            response: 'text', // response type
            success: function (data) {
                location.href = '/restwebservice/index.html';
            },
            statusCode: {
            	// HTTP 409 - Conflict
            	409: function (data) {
            		alert(data.responseText);	
            	}
            }
        });   
    return false;
    });
});