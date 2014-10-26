$(document).ready(function() {
    $('#create_post').submit(function(e) {
        e.preventDefault();
        var post = $('#post').val();        
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/createpost',
            crossDomain: true,
            data: {'post': post},
            response: 'text', // response type
            success: function (data) {
                location.href = '/restwebservice/index.html';
            },
            statusCode: {
            	// HTTP 409 - Conflict
            	409: function (data) {
            		$('#status').html(data.responseText);	
            	}
            }

        });

   
    return false;
    });

});