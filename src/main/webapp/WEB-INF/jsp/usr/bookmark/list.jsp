<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<section class="center list">
	<div class="center_box con">
		<c:forEach var="bookmark" items="${bookmarkList}" varStatus="loop">
			<c:if test="${!empty bookmarkList}">
				<div class="content_box">
					<div class="contents flex">
						<div class="location_box">무야호</div>
						<div class="img_box flex">
							<a class="detail_btn flex"
								href="javascript:popup(${loop.index});"
								onclick="getReplyList(${bookmark.eventId});">
								<img src="/resource/image/image_${bookmark.eventId}.jpg" alt="image" />
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
						</div>
					</div>
				</div>
				<div class="detail_bg" id="detail_bg_${loop.index }"></div>
				<div class="detail_box" id="detail_${loop.index}">
					<div class="flex">
						<div class="dt_content_box">
							<div class="dt_img">
								<img src="/resource/image/image_${bookmark.eventId}.jpg" alt="" />
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
							<div class="dt_location flex">무야야호</div>
							<div class="dt_content_body">이벤트 정보래</div>
							<div class="dt_reply" id="dt_reply_${loop.index}"></div>
							<div class="dt_reply_edit">
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
		<c:if test="${empty bookmarkList}">
			<div class="no_events">
				<span>NO RESULTS FOUND</span>
			</div>
		</c:if>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>