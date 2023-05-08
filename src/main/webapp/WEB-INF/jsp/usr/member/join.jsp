<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="SIGN UP"/>
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
			
			var passwordConfirm = $('#loginPwConfirm').val().trim();
			
			if (passwordConfirm.length === 0) {
				alert('비밀번호 확인을 입력해주세요.');
				$('#loginPwConfirm').focus();
				return;
			}

			if (password !== passwordConfirm) {
				alert('비밀번호를 확인해주세요.');
				$('#loginPw').val(''); // password 입력값 초기화
				$('#loginPwConfirm').val(''); // password-confirm 입력값 초기화
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
			<form action="../member/doJoin" method="POST" id="signUpForm">
				<h1>회원가입</h1>
				<div class="input_box flex fd-c flex-ai-c">
					<label for="loginId">
						<span>아이디</span>
					</label>
						<input required type="text" name="loginId" placeholder="아이디를 입력하세요" id="loginId"/>
					<label for="loginPw">
						<span>비밀번호</span>
					</label>
						<input required type="password" name="loginPw" placeholder="비밀번호를 입력하세요"  id="loginPw"/>
					<label for="loginPwConfirm">
						<span>비밀번호 확인</span>
					</label>
						<input required type="password" name="loginPwConfirm" placeholder="비밀번호를 입력하세요"  id="loginPwConfirm"/>
					<label for="nickname">
						<span>닉네임</span>
					</label>
						<input required type="text" name="nickname" placeholder="닉네임을 입력하세요"  id="nickname"/>
					<label for="email">
						<span>이메일</span>
					</label>
						<input required type="email" name="email" placeholder="이메일을 입력하세요"  id="email"/>
					<button id="submit_btn" type="submit">가입</button>
				</div>
			</form>
		</div>
<%@ include file="../common/foot.jspf"%>