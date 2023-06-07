<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="searchTypeCode" value="event" />
<%@ include file="../common/head.jspf"%>
<style>
.eventList_box {
	padding-top: 20px;
}

.eventList_box>h1 {
	text-align: center;
}

.eventList_box>a {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100px;
	height: 40px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: auto;
	margin-right: 10px;
	margin-bottom: 10px;
}

.eventList_box>a:active {
	scale: 0.9;
}

.eventList .event img {
	width: 60px;
}

.eventList .event img:hover {
	scale: 2.5;
}

.eventList>ul {
	font-size: 1.3rem;
}

.eventList ul>li {
	text-align: center;
	border: 1px solid black;
	width: calc(100%/ 11);
}

.event>ul>li {
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
<section class="eventList_box">
	<h1>이벤트 리스트</h1>
	<a href="/admin/manage/showAddEvent">추가</a>
	<div class="eventList">
		<ul class="flex">
			<li>No.</li>
			<li>Img.</li>
			<li>등록 날짜</li>
			<li>수정 날짜</li>
			<li>장르</li>
			<li>장소</li>
			<li>제목</li>
			<li>내용</li>
			<li>진행 시간</li>
			<li>수정</li>
			<li>삭제</li>
		</ul>
		<c:forEach var="event" items="${events}">
			<div class="event">
				<ul class="flex">
					<li>${event.id}</li>
					<li>
						<img src="${rq.getImgUri(event.id)}" onerror="${rq.profileFallbackImgOnErrorHtml}" alt="" />
					</li>
					<li>${event.regDate}</li>
					<li>${event.updateDate}</li>
					<li>${event.extra__genreName}</li>
					<li>${event.location}</li>
					<li>${event.title}</li>
					<li>${event.detail}</li>
					<li>${event.duration}</li>
					<li>
						<a href="/admin/manage/showModifyEvent?id=${event.id}">
							<i class="fa-regular fa-pen-to-square" style="color: #000000;"></i>
						</a>
					</li>
					<li>
						<a href="/admin/manage/deleteEvent?id=${event.id}"
							onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">
							<i class="fa-regular fa-trash-can" style="color: #000000;"></i>
						</a>
					</li>
				</ul>
			</div>
		</c:forEach>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>