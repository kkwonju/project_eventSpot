<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<script>
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

</script>

<section class="center list">
	<div class="center_box con">
		<c:forEach var="event" items="${events}" varStatus="loop">
			<c:if test="${!empty events}">
				<div class="content_box">
					<div class="contents flex">
						<div class="location_box">${event.location}</div>
						<div class="img_box flex">
							<a class="detail_btn flex"
								href="javascript:popup(${loop.index});"
								onclick="getReplyList(${event.id});">
								<img src="/resource/image/image_${event.id}.jpg" alt="image" />
							</a>
						</div>
						<div class="reaction_box">
							<button class="js_collect_btn" id="js_collect_btn_${loop.index}">
								<i class="unselected fa-regular fa-bookmark"></i>
								<i class="selected fa-solid fa-bookmark"></i>
							</button>
							+1.2k
						</div>
						<div class="reply_box pl-5">
							<c:forEach var="reply" items="${replyList}" begin="0" end="1">
								<div class="reply_content">
									<span class="reply_writer">${reply.extra__writer}</span>
									<span class="reply_body">${reply.body}</span>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="detail_bg" id="detail_bg_${loop.index }"></div>
				<div class="detail_box" id="detail_${loop.index}">
					<div class="flex">
						<div class="dt_content_box">
							<div class="dt_img">
								<img src="/resource/image/image_${event.id}.jpg" alt="" />
							</div>
						</div>
						<div class="dt_reply_box flex fd-c">
							<%-- <c:if test="예매가 가능한 공연인지 체크"> --%>
							<a class="side_btn exit_btn">
								<i class="fa-solid fa-xmark"></i>
							</a>
							<a
								href="http://ticket.interpark.com/TiKi/Special/TPRegionReserve.asp?Region=42061&RegionName=%B4%EB%C0%FC"
								target="_blank" class="side_btn reservation_btn">예매</a>
							<%-- </c:if> --%>
							<div class="dt_location flex">${event.location}</div>
							<div class="dt_content_body">${event.title}</div>
							<div class="dt_reply" id="dt_reply_${loop.index}"></div>
							<div class="dt_reply_edit">
								<form name="reply_form" method="POST" onsubmit="writeReply(this, ${loop.index}); return false;">
									<div class="flex flex-ai-c">
										<input id="reply_memberId_${loop.index}" type="hidden" name="memberId"
											value="${rq.loginedMemberId}" />
										<input id="reply_relId_${loop.index}" type="hidden" name="relId" value="${event.id}" />
										<textarea id="reply_body_${loop.index}" autofocus autocomplete="off" name="body"
											style="resize: none;" placeholder="댓글달기"></textarea>
										<button>게시</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
		<c:if test="${empty events}">
			<div class="no_events">
				<span>NO RESULTS FOUND</span>
			</div>
		</c:if>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>