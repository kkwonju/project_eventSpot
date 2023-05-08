<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="LOGIN"/>
<%@ include file="../common/head.jspf"%>
<script type="text/javascript">
	$(window).on('pageshow', function(event) {
		  // 페이지가 보여질 때 submit 버튼 활성화
		  $('#submit-btn').prop('disabled', false);
		});

	$(document).ready(function() {
		$('#memberForm').on('submit', function(e) {
			e.preventDefault(); // 기본 이벤트 동작을 중지
			var form = this;

			var password = $('#loginPw').val().trim();

			if (password.length === 0) {
				alert('비밀번호를 입력해주세요.');
				$('#loginPw').focus();
				return;
			}
		
			// 중복 제출을 막기 위해 submit 버튼 비활성화
			$('#submit_btn').prop('disabled', true);
			form.submit();
	});
</script>

<c:set var="pageTitle" value="Member Join"/>
		<div class="memberForm">
			<form action="../member/doLogin" method="POST" id="loginForm">
				<h1>로그인</h1>
				<div class="input_box flex fd-c flex-ai-c">
					<label for="loginId">
						<span>아이디</span>
					</label>
						<input required type="text" name="loginId" placeholder="아이디를 입력하세요" id="loginId"/>
					<label for="loginPw">
						<span>비밀번호</span>
					</label>
						<input required type="password" name="loginPw" placeholder="비밀번호를 입력하세요"  id="loginPw"/>
					<button id="submit_btn" type="submit">로그인</button>
				</div>
			</form>
		</div>
<%@ include file="../common/foot.jspf"%>