<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<section class="center list flex">
	<div class="center_box con">
		<c:forEach var="event" items="${events}">
			<div class="content_box">
				<div class="contents flex">
					<div class="location_box">
						${event.location}
					</div>
					<div class="img_box flex">
						<a class="detail_btn flex">
							<img src="/resource/image/image_20.jpg" alt="image" />
						</a>
					</div>
					<div class="reaction_box">
						<button class="like_btn">
							<i class="fa-regular fa-bookmark"></i>
							<i class="fa-solid fa-bookmark"></i>
						</button>
						+1.2k
					</div>
					<div class="reply_box pl-5">
						kkwo
						<div>
							첫 번째 댓글입니다
						</div>
						kkwo2
						<div>
							두 번째 댓글입니다
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>