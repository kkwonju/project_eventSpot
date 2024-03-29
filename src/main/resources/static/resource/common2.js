//댓글 불러오기
function getReplyList(eventId) {
	var action = "/usr/reply/list";

	$.get(action, {
		relTypeCode: 'event',
		relId: eventId,
	}, function(data) {
		var replies = data.data1;
		var actorId = data.data2;
		displayReplies(replies, actorId); // 댓글 데이터를 화면에 출력하는 함수 호출
		scrollToBottom(eventId);
	}, 'json');
}

// 댓글 삭제
function removeReplyBtn(replyId) {
	const result = window.confirm("정말 삭제하시겠습니까?");
	if (result) {
		var action = "/usr/reply/delete";

		$.post(action, {
			replyId: replyId,
		}, function(data) {
			var relId = data.data1;
//			displayReplies(replyList);
			getReplyList(relId);
		}, 'json');
	} else {
		return;	
	}
}

// 이벤트 디테일 페이지 댓글 보여주기
function displayReplies(replies, actorId) {
	var replyContainer = $('.dt_reply'); // 댓글이 출력될 컨테이너 요소 선택

	// 기존 댓글 삭제
	replyContainer.empty();

	// 새로운 댓글 출력
	$.each(replies, function(index, reply) {
		var replyHtml = '<div class="reply_content">'
			+ '<span class="reply_writer">' + reply.extra__writer + ' ' + '</span>'
			+ '<span class="reply_body">' + reply.body + '</span>'
			+ '<div class="reply_add">'
			+ '<span style="color:#807e7e;">' + reply.regDate.substring(5, 7) + reply.regDate.substring(8, 10) + " " + reply.regDate.substring(10, 16) + '&nbsp;&nbsp;';
		if (actorId == reply.memberId) {
			replyHtml += '<a onclick="removeReplyBtn(' + reply.id + ');">' + '<i class="fa-regular fa-trash-can" style="scale:0.8; color:#807e7e;">' + '</i>' + '</a>';
		}
		replyHtml += '</span>' + '</div>' + '</div>';
		replyContainer.append(replyHtml);
	});
}

// 댓글 작성
function writeReply(eventId) {

	var relId = $('#reply_relId_' + eventId).val();
	var body = $('#reply_body_' + eventId).val();

	if (body === "") {
		event.preventDefault();
		alert("댓글을 입력해주세요");
		return false;
	}
	var action = "/usr/reply/doWrite";

	$.get(action, {
		relTypeCode: 'event',
		relId: relId,
		body: body,
	}, function(data) {
		var replyList = data.data1;
		$('#reply_body_' + eventId).val("");
//		displayReplies(replyList); // 댓글 데이터를 화면에 출력하는 함수 호출
		getReplyList(eventId); // 댓글 데이터를 화면에 출력하는 함수 호출
//		scrollToBottom(eventId);
	}, 'json');
};



//Enter 키 기능 멈추고 메서드 실행
function handleKeyDown(e, eventId) {
	if (e.keyCode === 13) { // Enter 키의 keyCode는 13
		e.preventDefault(); // 기본 동작인 줄바꿈을 막습니다
		writeReply(eventId);
	}
}

//댓글 작성 후 화면 댓글창 맨밑로 이동
function scrollToBottom(eventId) {
	var niceElement = document.querySelector('#dt_reply_' + eventId);

	if (niceElement) {
		var scrollHeight = niceElement.scrollHeight;
		var clientHeight = niceElement.clientHeight;

		niceElement.scrollTop = scrollHeight - clientHeight;
	}
}

// 이벤트 리스트 페이지 댓글 보여주기
function displayReplyOnEventList(replies, num) {
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
			+ '</div>' + '</div>';
		replyContainer.append(replyHtml);
	});
}

// 북마크 기능

