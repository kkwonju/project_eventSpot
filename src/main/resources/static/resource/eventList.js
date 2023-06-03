// 이벤트 불러오기 제한
var isExecuted = false;

// 댓글 불러오기
function getReplyList(eventId) {
	var action = "/usr/reply/list";

	$.get(action, {
		relTypeCode: 'event',
		relId: eventId,
	}, function(data) {
		var replies = data.data1;
		displayReplies(replies); // 댓글 데이터를 화면에 출력하는 함수 호출
	}, 'json');
}

// 이벤트 디테일 페이지 댓글 보여주기
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

// 이벤트 리스트 페이지 댓글 보여주기
function displayReplyOnEventList(replies, num){
	var replyContainer = $('.reply_box_' + num); // 댓글이 출력될 컨테이너 요소 선택

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

// 댓글 작성
function writeReply(eventId) {

	var relId = $('#reply_relId_' + eventId).val();
	var body = $('#reply_body_' + eventId).val();
	var action = "/usr/reply/doWrite";

	$.get(action, {
		relTypeCode: 'event',
		relId: relId,
		body: body,
	}, function(data) {
		var replyList = data.data1;
		$('#reply_body_' + eventId).val("");
		displayReplies(replyList); // 댓글 데이터를 화면에 출력하는 함수 호출
		scrollToBottom(eventId);
	}, 'json');
};


// 이벤트 불러오기
function getEventList() {
	var offset = $('#center_box').children('.content_box').length;

	var action = "/usr/event/getEventList";

	$.ajax({
		url: action, // 요청을 보낼 URL
		method: 'POST', // HTTP 요청 메서드 (GET, POST, 등)
		data: {
			offset: offset,
		}, // 요청에 포함될 데이터 (객체 형태)
		success: function(data) {
			// 성공적으로 요청을 받았을 때 실행될 함수
			var events = data.data1;
			var loginedMemberId = data.data2;
			displayEvent(events, loginedMemberId);
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
function displayEvent(events, loginedMemberId) {
	var eventsContainer = $('.center_box.con');

	var objLength = Object.keys(events).length;
	$.each(events, function(index, event) {
		if (objLength > 0) {
			var listEventHtml = 
				  '<div class="content_box">'
				+ 	'<div class="contents flex">'
				+ 		'<div class="location_box">' + event.location + '</div>'
				+ 		'<div class="img_box flex">'
				+ 			'<a class="detail_btn flex" href="javascript:popup(' + event.id + ');" onclick="getReplyList(' + event.id + ');">'
				+ 				'<img src="/resource/image/image_' + event.id + '.jpg" alt="image" />'
				+ 			'</a>'
				+ 		'</div>'
				+ 		'<div class="reaction_box">'
				+ 			'<button class="js_collect_btn" id="js_collect_btn_' + index + '">'
				+ 				'<i class="unselected fa-regular fa-bookmark"></i>'
				+		 		'<i class="selected fa-solid fa-bookmark"></i>'
				+ 			'</button>'
				+ 			'+1.2k'
				+ 		'</div>'
				+	 	'<div class="reply_box pl-5" id="reply_box_' + event.id + '"></div>'
				+ 	'</div>'
				+ '</div>'
				+ '<div class="detail_bg" id="detail_bg_' + event.id + '"></div>'
				+ '<div class="detail_box" id="detail_' + event.id + '">'
				+	'<div class="flex">'
				+ 		'<div class="dt_content_box">'
				+ 			'<div class="dt_img">'
				+ 				'<img src="/resource/image/image_' + event.id + '.jpg" alt="" />'
				+ 			'</div>'
				+ 		'</div>'
				+		'<div class="dt_reply_box flex fd-c">'
				+ 			'<a class="side_btn exit_btn">'
				+ 				'<i class="fa-solid fa-xmark"></i>'
				+ 			'</a>'
				+ 			'<a href="http://ticket.interpark.com/TiKi/Special/TPRegionReserve.asp?Region=42061&RegionName=%B4%EB%C0%FC" target="_blank" class="side_btn reservation_btn">예매</a>'
				+ 			'<div class="dt_location flex">' + event.location + '</div>'
				+ 			'<div class="dt_content_body">' + event.title + '</div>'
				// 댓글 삽입부
				+ 			'<div class="dt_reply" id="dt_reply_' + event.id + '"></div>'
				if(loginedMemberId != 0){
					listEventHtml = listEventHtml
					+ 			'<div class="dt_reply_edit">'
					+ 				'<form onsubmit="writeReply(' + event.id + '); return false;">'
					+ 					'<div class="flex flex-ai-c">'
					+ 						'<input type="hidden" id="reply_memberId_' + event.id + '" name="memberId" value="' + loginedMemberId + '" />'
					+ 						'<input type="hidden" id="reply_relId_' + event.id + '" name="relId" value="' + event.id + '" />'
					+ 						'<textarea autofocus autocomplete="off" id="reply_body_' + event.id + '" name="body" style="resize: none;" placeholder="댓글달기" onkeydown="handleKeyDown(e, '+ event.id +')"></textarea>'
					+ 						'<button onclick="writeReply(' + event.id + ');" type="button">게시</button>'
					+ 					'</div>'
					+ 				'</form>'
					+ 			'</div>'
				}
				+ 		'</div>'
				+ 	'</div>'
				+ '</div>'
		} else {
			listEventHtml = 
					'<div class="no_events">'
				+ 		'<span>NO RESULTS FOUND</span>'
				+ 	'</div>'
		}
		eventsContainer.append(listEventHtml);
	});
}

// Enter 키 기능 멈추고 메서드 실행
function handleKeyDown(e, eventId) {
  if (e.keyCode === 13) { // Enter 키의 keyCode는 13입니다
    e.preventDefault(); // 기본 동작인 줄바꿈을 막습니다
    enterReply(eventId); // 특정 메서드를 실행합니다
  }
}

function enterReply(eventId) {
	writeReply(eventId);
}

// 스크롤 이벤트 리스너 추가
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

// 댓글 작성 후 화면 댓글창 맨밑로 이동
function scrollToBottom(eventId) {
	var niceElement = document.querySelector('#dt_reply_' + eventId);

	if (niceElement) {
		var scrollHeight = niceElement.scrollHeight;
		var clientHeight = niceElement.clientHeight;

		niceElement.scrollTop = scrollHeight - clientHeight;
	}
}

// 이벤트 불러오기 실행
getEventList();