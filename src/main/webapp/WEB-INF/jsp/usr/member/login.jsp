<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

 <section class="center">
	<div class="memberForm loginForm">
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
				<div>
					<a class="find_btn" href="../member/findLoginId">아이디 찾기</a>
					<a class="find_btn" href="../member/findLoginPw">비밀번호 찾기</a>
				</div>
			</div>
		</form>
	</div>
 </section>
<%@ include file="../common/foot.jspf"%>