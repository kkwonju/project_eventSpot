<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="searchTypeCode" value="member"/>
<%@ include file="../common/head.jspf"%> 
<style>
.memberList_box {
	padding-top: 20px;
}

.memberList_box > h1 {
	text-align: center;
}

.memberList .member img {
	width: 60px;
}

.memberList .member img:hover {
	scale: 2.5;
}

.memberList > ul {
	font-size: 1.3rem;
}

.memberList ul > li {
	text-align: center;
	border: 1px solid black;
	width: calc(100%/ 10);
}

.member > ul > li {
	display: flex;
	height: 50px;
	justify-content: center;
	align-items: center;
}

</style>
	<section class="memberList_box">
		<h1>회원 리스트</h1>
		<div class="memberList">
			<ul class="flex">
				<li>No.</li>
				<li>등록 날짜</li>
				<li>수정 날짜</li>
				<li>아이디</li>
				<li>권한 레벨</li>
				<li>닉네임</li>
				<li>이메일</li>
				<li>탈퇴 여부(1=탈퇴)</li>
				<li>탈퇴 날짜</li>
				<li>삭제</li>
			</ul>
			<c:forEach var="member" items="${members}">
				<div class="member">
					<ul class="flex">
						<li>${member.id}</li>
						<li>${member.regDate}</li>
						<li>${member.updateDate}</li>
						<li>${member.loginId}</li>
						<li>${member.authLevel == 7 ? '관리자' : '일반'}</li>
						<li>${member.nickname}</li>
						<li>${member.email}</li>
						<li>${member.delStatus}</li>
						<li>${member.delDate}</li>
						<li>
							<a href="/admin/manage/deleteMember?id=${member.id}"
								onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">
								<i class="fa-regular fa-trash-can" style="color: #000000;"> </i>
							</a>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</section>
	<%@ include file="../common/foot.jspf"%> 