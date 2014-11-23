$(document).ready(function() {
    $('#create_post').submit(function(e) {
        e.preventDefault();
        var post1 = $('#post1').val(); 
        var post = $('#post').val(); 
        alert(post1);
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/createpost',
            crossDomain: true,
            data: {'post1': post1, 'post': post},
            response: 'text', // response type
            success: function (data) {
                location.href = '/restwebservice/index1.html';
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