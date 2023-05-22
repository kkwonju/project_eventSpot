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
		}, function(data){
			var replies = data.data1;
		}, 'json')
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
								href="javascript:popup(${loop.index});">
								<img src="/resource/image/image_${event.imgId}.jpg" alt="image" />
							</a>
						</div>
						<div class="reaction_box">
							<button class="like_btn">
								<i class="fa-regular fa-bookmark"></i>
								<!-- <i class="fa-solid fa-bookmark"></i> -->
							</button>
							+1.2k
						</div>
						<div class="reply_box pl-5">
							<c:forEach var="reply" items="${replies}" begin="0" end="1">
								<div class="reply_content">
									<span class="reply_writer">${reply.extra__writer}</span>
									<span class="reply_body">${reply.body}</span>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="detail_bg"></div>
				<div class="detail_box" id="detail_${loop.index}">
					<div class="flex">
						<div class="dt_content_box">
							<div class="dt_img">
								<img src="/resource/image/image_${event.imgId}.jpg" alt="" />
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
							<a href="javascript:getReplyList(${event.id})">댓글가져와</a>
							<div class="dt_reply">
								<c:forEach var="reply" items="${replies}">
									<div class="reply_content">
										<span class="reply_writer">${reply.extra__writer}</span>
										<span class="reply_body">${reply.body}</span>
										<div class="reply_add">
											<span>${reply.regDate.substring(5, 10)}</span>
											<a>reply</a>
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="dt_reply_edit">
								<form action="">
									<div class="flex flex-ai-c">
										<input type="hidden" name="memberId"
											value="${rq.loginedMemberId}" />
										<textarea autofocus autocomplete="off" name="body"
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