<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="main" />
<%@ include file="../common/head.jspf"%>

<div class="center_box con">
	<c:forEach var="event" items="${events}">
		<div class="content_box">
			<div class="contents flex">
				<div class="location_box">
					${event.location}
				</div>
				<div class="img_box">
					<img src="/resource/image/image_23.jpg" alt="image" />
				</div>
				<div class="reaction_box">
					<button>
						<i class="fa-sharp fa-regular fa-heart"></i>
					</button>
					리액션
				</div>
				<div class="reply_box">댓글</div>
			</div>
		</div>
	</c:forEach>
</div>


<%@ include file="../common/foot.jspf"%>