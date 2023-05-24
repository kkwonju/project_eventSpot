<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<section class="center list">
	<div class="center_box con"></div>
</section>

<script>
// 	function checkVisible( element, check = 'above' ) {
// 	    const viewportHeight = $(window).height(); // Viewport Height
// 	    const scrolltop = $(window).scrollTop(); // Scroll Top
// 	    const y = $(element).offset().top;
// 	    const elementHeight = $(element).height();   
	    
// 	    // 반드시 요소가 화면에 보여야 할경우
// 	    if (check == "visible") 
// 	    	return ((y < (viewportHeight + scrolltop)) && (y > (scrolltop - elementHeight)));
	        
// 	    // 화면에 안보여도 요소가 위에만 있으면 (페이지를 로드할때 스크롤이 밑으로 내려가 요소를 지나쳐 버릴경우)
// 	    if (check == "above") 
// 	    	return ((y < (viewportHeight + scrolltop)));
// 	}
	
// 	// 리소스가 로드 되면 함수 실행을 멈출지 말지 정하는 변수
// 	let isVisible = false;
	
// 	// 이벤트에 등록할 함수
// 	const func = function () {
// 	    if ( !isVisible && checkVisible('.center_box') ) {
	        
// 	        isVisible = true;
// 	    }
	    
// 	    // 만일 리소스가 로드가 되면 더이상 이벤트 스크립트가 있을 필요가 없으니 삭제
// 	    isVisible && window.removeEventListener('scroll', func);
// 	}
	
// 	// 스크롤 이벤트 등록
// 	window.addEventListener('scroll', func);




	function getReplyList() {
		var action = "/usr/reply/list";
	
		$.get(action, {
			relTypeCode : 'event',
			relId : 1,
		}, function(data) {
			var replies = data.data1;
			displayReplies(replies); // 댓글 데이터를 화면에 출력하는 함수 호출
		}, 'json');
	}
	
	function getEventList(){}
	var action = "/usr/event/getEventList";
	
	$.ajax({
		  url: action, // 요청을 보낼 URL
		  method: 'GET', // HTTP 요청 메서드 (GET, POST, 등)
		  data: {}, // 요청에 포함될 데이터 (객체 형태)
		  success: function(data) {
		    // 성공적으로 요청을 받았을 때 실행될 함수
		    // 응답 데이터는 'response' 매개변수로 전달됨
		    var events = data.data1;
		    displayEvent(events);
		  },
		  error: function(xhr, status, error) {
		    // 요청이 실패했을 때 실행될 함수
		    // 에러 정보는 'xhr', 'status', 'error' 매개변수로 전달됨
		    console.log("실패", error);
		  }
	});
	
	function displayEvent(events){
		var eventsContainer = $('.center_box.con');
		
		var objLength = Object.keys(events).length;
		$.each(events, function(index, event) {
			if(objLength > 0){
				var listEventHtml = 
					'<div class="content_box">'
				+	'<div class="contents flex">'
				+	'<div class="location_box">' + event.location 
				+	'</div>'
				+	'<div class="img_box flex">'
				+ 	'<a class="detail_btn flex" href="javascript:popup(' + index + ');" onclick="getReplyList(' + event.id + ');">'
				+	'<img src="/resource/image/image_' + event.imgId + '.jpg" alt="image" />'
				+	'</a>'
				+	'</div>'
				+	'<div class="reaction_box">'
				+	'<button class="js_collect_btn" id="js_collect_btn_' + index + '">'
				+	'<i class="unselected fa-regular fa-bookmark"></i>'
				+	'<i class="selected fa-solid fa-bookmark"></i>'
				+	'</button>'
				+ 	'+1.2k'
				+ 	'</div>'
				+	'<div class="reply_box pl-5"></div>'
				+	'</div>'
				+	'</div>'
				+	'<div class="detail_bg" id="detail_bg_' + index +'"></div>'
				+	'<div class="detail_box" id="detail_' + index + '">'
				+	'<div class="flex">'
				+	'<div class="dt_content_box">'
				+	'<div class="dt_img">'
				+	'<img src="/resource/image/image_' + event.imgId + '.jpg" alt="" />'
				+	'</div>'
				+	'</div>'
				+	'<div class="dt_reply_box flex fd-c">'
				+	'<a class="side_btn exit_btn">'
				+	'<i class="fa-solid fa-xmark"></i>'
				+	'</a>'
				+	'<a href="http://ticket.interpark.com/TiKi/Special/TPRegionReserve.asp?Region=42061&RegionName=%B4%EB%C0%FC" target="_blank" class="side_btn reservation_btn">예매</a>'
				+	'<div class="dt_location flex">' + event.location + '</div>'
				+	'<div class="dt_content_body">' + event.title + '</div>'
				+	'<div class="dt_reply"></div>'
				+	'<div class="dt_reply_edit">'
				+	'<form action="">'
				+	'<div class="flex flex-ai-c">'
				+	'<input type="hidden" name="memberId" value="${rq.loginedMemberId}" />'
				+	'<textarea autofocus autocomplete="off" name="body" style="resize: none;" placeholder="댓글달기"></textarea>'
				+	'<button>게시</button>'
				+	'</div>'
				+	'</form>'
				+	'</div>'
				+	'</div>'
				+	'</div>'
				+	'</div>'
			} else {
				listEventHtml = '<div class="no_events">'
					+	'<span>NO RESULTS FOUND</span>'
					+	'</div>'
			}
			
			eventsContainer.append(listEventHtml);
		});
	}


	
	function displayReplies(replies) {
		var replyContainer = $('.dt_reply'); // 댓글이 출력될 컨테이너 요소 선택
	
		// 기존 댓글 삭제
		replyContainer.empty();
	
		// 새로운 댓글 출력
		$.each(replies, function(index, reply) {
			replyContainer.append(replyHtml);
		});
	}
	
	getEventList();
	getEventList();
</script>


<%@ include file="../common/foot.jspf"%>