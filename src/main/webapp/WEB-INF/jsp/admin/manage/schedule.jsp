<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="searchTypeCode" value="schedule"/>
<%@ include file="../common/head.jspf"%>
<style>
.scheduleList_box {
	padding-top: 20px;
}

.scheduleList_box > h1 {
	text-align: center;
}

.scheduleList_box > a {
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

.scheduleList_box>a:active {
	scale: 0.9;
}

.scheduleList .schedule img:hover {
	scale: 2.5;
}

.scheduleList>ul {
	font-size: 1.3rem;
}

.scheduleList ul>li {
	text-align: center;
	border: 1px solid black;
	width: calc(100%/ 7);
}

.schedule>ul>li {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 40px;
}
</style>
<section class="scheduleList_box">
	<h1>일정 목록</h1>
	<a href="/admin/manage/showAddSchedule">추가</a>
	<div class="scheduleList">
		<ul class="flex">
			<li>No.</li>
			<li>공연일</li>
			<li>등록 날짜</li>
			<li>수정 날짜</li>
			<li>이벤트 ID</li>
			<li>수정</li>
			<li>삭제</li>
		</ul>
		<c:forEach var="schedule" items="${scheduleList}">
			<div class="schedule">
				<ul class="flex">
					<li>${schedule.id}</li>
					<li>${schedule.eventDate}</li>
					<li>${schedule.regDate}</li>
					<li>${schedule.updateDate}</li>
					<li>${schedule.eventId}</li>
					<li>
						<a href="/admin/manage/showUpdateSchedule?id=${schedule.id}">
							<i class="fa-regular fa-pen-to-square" style="color: #000000;"></i>
						</a>
					</li>
					<li>
						<a href="/admin/manage/deleteSchedule?id=${schedule.id}"
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