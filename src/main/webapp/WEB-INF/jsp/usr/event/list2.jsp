<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<section class="center list">
		<div class="center_box con" id="center_box"></div>
</section>

<script>
	var isExecuted = false;

	// 댓글 불러오기
	function getReplyList(eventId) {
		var action = "/usr/reply/list";
	
		$.get(action, {
			relTypeCode : 'event',
			relId : eventId,
		}, function(data) {
			var replies = data.data1;
			displayReplies(replies); // 댓글 데이터를 화면에 출력하는 함수 호출
		}, 'json');
	}
	
	// 이벤트 불러오기
	function getEventList(){
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
			    isExecuted = false;
			  },
			  error: function(xhr, status, error) {
			    // 요청이 실패했을 때 실행될 함수
			    // 에러 정보는 'xhr', 'status', 'error' 매개변수로 전달됨
			    console.log("실패", error);
			  }
		});
	};

	// 이벤트 보여주기
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
					// 댓글 삽입부
				+	'<div class="dt_reply"></div>'
				+	'<div class="dt_reply_edit">'
				+	'<form action="">'
				+	'<div class="flex flex-ai-c">'
				+	'<input type="hidden" name="memberId" value="rq.loginedMemberId" />'
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

	// 댓글 보여주기
	function displayReplies(replies) {
		var replyContainer = $('.dt_reply'); // 댓글이 출력될 컨테이너 요소 선택
	
		// 기존 댓글 삭제
		replyContainer.empty();
	
		// 새로운 댓글 출력
		$.each(replies, function(index, reply) {
			var replyHtml = '<div class="reply_content">'
				+ '<span class="reply_writer">' + reply.extra__writer
				+ '</span> <span class="reply_body">' + reply.body
				+ '</span>' + '<div class="reply_add">' + '<span>'
				+ reply.regDate.substring(5, 10) + '</span>'
				+ '<a href="#"> reply</a>' + '</div>' + '</div>';
			replyContainer.append(replyHtml);
		});
	}
	
	getEventList();
	
// 	var divElement = document.getElementById('center_box'); // 대상 div 요소의 ID를 설정해주세요
// 	var divHeight = divElement.offsetHeight;
// 	console.log("div 요소의 높이: " + divHeight + "px");
	
	
	window.addEventListener('scroll', function() {
		// 엘리먼트 지정
		var divElement = document.getElementById('center_box'); // 대상 div 요소의 ID를 설정해주세요
		// 화면 높이 지정
		var windowHeight = window.innerHeight;
		// 스크롤된 Y 좌표 지정
		var scrollPosition = window.scrollY || window.pageYOffset;

		// 엘리먼트의 top 위치값
		var elementOffsetTop = divElement.offsetTop;
		// 엘리먼트의 높이 (정수값)
		var elementHeight = divElement.offsetHeight;
		
		// 엘리먼트의 중간값 = 엘리먼트 top + 엘리먼트 높이 - 200px;
		var elementMiddle = elementOffsetTop + elementHeight - 200;
		// Y 좌표 중간값 = 스크롤 Y좌표 + 화면 높이 - 200px
		var scrollMiddle = scrollPosition + windowHeight - 200;

		if (scrollMiddle >= elementMiddle - windowHeight / 4 && scrollMiddle <= elementMiddle + windowHeight / 4 && !isExecuted) {
			// 화면 중간 근처에 위치했을 때 실행될 코드를 여기에 작성하세요
			getEventList();
			isExecuted = true;
		}
	});
</script>


<%@ include file="../common/foot.jspf"%>