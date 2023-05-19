<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>
<style>
.adm_search_box {
	width: 100%;
	height: 100px;
	position: relative;
}

.adm_search_box > input {
	flex-grow: 5;
	padding-left: 5px;
	font-size: 1.1rem;
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

.foundList-box {
	width: 80%;
	margin: 0 auto;
	height: 300px;
	border: 1px solid red;
}
</style>
<!--
	구성 계획 
	1. 리스트 제목 박스
	2. 리스트 아이템 박스
		ㄱ. 
	3. 리스트 더보기 박스
-->

<form action="/admin/home/search">
	<div class="adm-search-box flex flex-jc-c flex-ai-c bd-red">
		<input class="sk_input" name="searchKeyword" type="text"
			value="${param.searchKeyword }" />
		<span class="input_placeholder">Search</span>
		<button type="submit">검색</button>
	</div>
</form>
<c:if
	test="${param.searchKeyword != null and param.searchKeyword != ''}">
	<div class="foundList-box">
		<div class="foundList-title">
			<h1>이벤트 리스트</h1>
		</div>
		<div class="foundList-contents">
			<c:forEach var="event" items="${events}">
				<div class="found_item_box bd-blue">${event.title}</div>
			</c:forEach>
		</div>
		<div class="foundList-add">
			<a href="#">더보기 &gt;</a>
		</div>
		<hr />
	</div>
	<div class="foundList-box">
		<div class="foundList-title">
			<h1>회원 리스트</h1>
		</div>
		<div class="foundList-contents">
			<c:forEach var="member" items="${members}" begin="0" end="4">
				<div class="found_item_box bd-blue">${member.nickname}</div>
			</c:forEach>
		</div>
		<div class="foundList-add">
			<a href="#">더보기 &gt;</a>
		</div>
		<hr />
	</div>
	<div class="foundList-box">
		<div class="foundList-title">
			<h1>일정 리스트</h1>
		</div>
		<div class="foundList-contents">
			<c:forEach var="schedule" items="${scheduleList}">
				<div class="found_item_box bd-blue">${schedule.eventTitle}</div>
			</c:forEach>
		</div>
		<div class="foundList-add">
			<a href="#">더보기 &gt;</a>
		</div>
	</div>
</c:if>

<%@ include file="../common/foot.jspf"%>