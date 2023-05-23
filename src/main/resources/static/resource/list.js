$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	        loadMorePosts();
	    }
	});

	function loadMorePosts() {
	    var offset = 2; // 현재까지 로드된 게시물의 수
	    var limit = 2; // 한 번에 가져올 게시물 수

	    $.ajax({
	        url: '/loadMorePosts',
	        type: 'GET',
	        data: {
	            offset: offset,
	            limit: limit
	        },
	        success: function(data) {
	            // 받은 데이터를 이용하여 동적으로 게시물을 생성하고 추가합니다.
	            $.each(data, function(index, post) {
	                var postHtml = 
	                $('.posts-container').append(postHtml);
	            });
	        }
	    });
	}