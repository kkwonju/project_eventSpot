<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

 <section class="center">
	<div class="member_box login_form">
		<form action="../member/doFindLoginId" method="POST">
			<h1>아이디 찾기</h1>
			<div class="input_box flex fd-c flex-ai-c">
				<label for="email">
					<span>이메일</span>
				</label>
				<input autocomplete="off" type="text" name="email" placeholder="이메일을 입력하세요" id="email"/>
				<button id="submit_btn" type="submit">찾기</button>
			</div>
		</form>
	</div>
 </section>
<%@ include file="../common/foot.jspf"%>