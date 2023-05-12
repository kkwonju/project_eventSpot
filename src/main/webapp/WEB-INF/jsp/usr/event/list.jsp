<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="main"/>
<%@ include file="../common/head.jspf"%>

		<div class="center_box bd-red">
			<div class="center_layout flex bd-pink">
				<c:forEach var="event" items="${events}">
					<div class="content_box bd-blue">
						<div class="content flex bd-green">
							<div class="profile_box bd-black">
								프로필
							</div>
							<div class="img_box bd-gold">
								<img src="https://picsum.photos/id/${event.id}/430/602" alt="image" />
								${event.location}
								${event.title}
								${event.detail}
							</div>
							<div class="reaction_box">
								<button style="cursor: pointer"><i class="fa-sharp fa-regular fa-heart"></i></button>
								리액션
							</div>
							<div class="reply_box">
								댓글
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

<%@ include file="../common/foot.jspf"%>