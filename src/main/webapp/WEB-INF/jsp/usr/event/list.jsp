<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<script>
	function popup(index) {

		$('.detail_bg').show();
		$('#detail_' + index).show();
		$('body').css('overflow', 'hidden');

		$('.detail_bg').on('click', function() {
			$('.detail_bg').css('display', 'none');
			$('#detail_' + index).css('display', 'none');
			$('body').css('overflow', 'auto');
		})
	}
</script>
<section class="center list flex">
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
								<!-- 							<i class="fa-solid fa-bookmark"></i> -->
							</button>
							+1.2k
						</div>
						<div class="reply_box pl-5">
							kkwo
							<div>첫 번째 댓글입니다</div>
							kkwo2
							<div>두 번째 댓글입니다</div>
						</div>
					</div>
				</div>
				<div class="detail_bg"></div>
				<div class="detail" id="detail_${loop.index}">
					<div class="flex">
						<div class="dt_content">
							<div class="dt_location flex">
								${event.location}
								<a href="#">예매</a>
							</div>
							<div class="dt_img">
								<img src="/resource/image/image_${event.imgId}.jpg" alt="" />
							</div>
							<!-- 						<div> -->
							<%-- 							${event.title} --%>
							<!-- 							<br /> -->
							<%-- 							${event.detail} --%>
							<!-- 						</div> -->
						</div>
						<div class="reply_box flex fd-c">
							<div class="dt_reply">
								kkwo
								<div>첫 번째 댓글입니다</div>
								<hr />
								kkwo2
								<div>두 번째 댓글입니다</div>
								<hr />
								kkwo
								<div>세 번째 댓글입니다</div>
								<hr />
								kkwo2
								<div>네 번째 댓글입니다</div>
								<hr />
								kkwo
								<div>다섯 번째 댓글입니다</div>
								<hr />
								kkwo2
								<div>여섯 번째 댓글입니다</div>
								<hr />
								kkwo
								<div>일곱 번째 댓글입니다</div>
								<hr />
								kkwo2
								<div>여덟 번째 댓글입니다</div>
								<hr />
								kkwo
								<div>아홉 번째 댓글입니다</div>
								<hr />
								kkwo2
								<div>열 번째 댓글입니다</div>
								<hr />
								kkwo
								<div>열 하나 번째 댓글입니다</div>
								<hr />
								kkwo2
								<div>열 둘 번째 댓글입니다</div>
								<hr />
							</div>
							<div class="input_reply">
								<form action="">
									<div class="flex">
										<textarea name="" id="" style="resize: none;"></textarea>
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