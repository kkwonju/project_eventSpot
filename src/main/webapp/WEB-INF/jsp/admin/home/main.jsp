<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>
<style>
.adm_search_box {
	font-family: 'SUITE-Regular', 'sans';
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 100px;
}

.adm_search_box>.search_layout {
	position: relative;
	width: 600px;
	height: 50px;
}

.input_placeholder {
	position: absolute;
	background-color: white;
	top: -10px;
	left: 10px;
	padding: 0 4px;
	border-radius: 5px;
	color: grey;
}

.adm_search_box>.search_layout>input {
	flex-grow: 5;
	padding-left: 5px;
	font-size: 1.1rem;
}

.list_box {
	width: 80%;
	margin: 0 auto;
	height: 300px;
	border: 1px solid red;
}

.list_box > .found_list_box {
	
}
</style>
<!--
	구성 계획 
	1. 리스트 제목 박스
	2. 리스트 아이템 박스
	3. 리스트 더보기 박스
-->

<form action="/admin/home/main">
	<div class="adm_search_box flex">
		<div class="search_layout flex">
			<input class="sk_input" name="searchKeyword" type="text"
				value="${param.searchKeyword }" />
			<span class="input_placeholder">Search</span>
			<button type="submit">검색</button>
		</div>
	</div>
</form>
<c:if
	test="${param.searchKeyword != null and param.searchKeyword != ''}">
	<div class="list_box">
		<h1>이벤트 리스트</h1>
		<div class="found_list_box">
			<c:forEach var="event" items="${events}">
				<div class="found_item_box bd-blue">${event.title}</div>
			</c:forEach>
		</div>
		<hr />
	</div>
	<div class="list_box">
		<h1>회원 리스트</h1>
		<div class="found_list_box">
			<c:forEach var="member" items="${members}" begin="0" end="4">
				<div class="found_item_box bd-blue">${member.nickname}</div>
			</c:forEach>
		</div>
		<hr />
	</div>
	<div class="list_box">
		<h1>일정 리스트</h1>
		<div class="found_list_box">
			<c:forEach var="schedule" items="${scheduleList}">
				<div class="found_item_box bd-blue">${schedule.eventTitle}</div>
			</c:forEach>
		</div>
	</div>
</c:if>

<%@ include file="../common/foot.jspf"%>