<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<section class="center">
	<div class="memberForm">
		<div class="profile">
			<h1>PROFILE</h1>
			<ul class="">
				<li>
					<div>
						아이디
						<p>${member.loginId}</p>
					</div>
				</li>
				<li>
					<div>
						닉네임
						<p>${member.nickname}</p>
					</div>
					<a href="">수정</a>
				</li>
				<li>
					<div>
						이메일
						<p>${member.email}</p>
					</div>
				</li>
			</ul>
			<p class="updateDate">sign in : ${member.regDate}</p>
			<p class="updateDate">last update : ${member.updateDate}</p>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>