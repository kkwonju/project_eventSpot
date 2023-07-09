<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<style>
	#submit_btn {
		background-color: black;	
	}
</style>
 <section class="center">
	<div class="member_box login_form">
		<form action="../member/doLogin" method="POST" id="loginForm">
			<h1>로그인</h1>
			<div class="input_box flex fd-c flex-ai-c">
				<label for="loginId">
					<span>아이디</span>
				</label>
				<input autocomplete="off" type="text" name="loginId" placeholder="아이디를 입력하세요"
					id="loginId" />
				<label for="loginPw">
					<span>비밀번호</span>
				</label>
				<input type="password" name="loginPw"
					placeholder="비밀번호를 입력하세요" id="loginPw" />
				<button id="submit_btn" type="submit">로그인</button>
			</div>
		</form>
	</div>
 </section>
<%@ include file="../common/foot.jspf"%>