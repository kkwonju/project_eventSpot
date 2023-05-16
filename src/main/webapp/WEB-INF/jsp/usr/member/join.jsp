<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<script type="text/javascript">
	let submitJoinFormDone = false;

	function submitJoinForm(form) {
		if (submitJoinFormDone) {
			alert('처리중입니다');
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value == 0) {
			alert('아이디를 입력해주세요');
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value == 0) {
			alert('비밀번호를 입력해주세요');
			return;
		}
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPwConfirm.value == 0) {
			alert('비밀번호 확인을 입력해주세요');
			return;
		}
		if (form.loginPwConfirm.value != form.loginPw.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.focus();
			return;
		}
		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value == 0) {
			alert('닉네임을 입력해주세요');
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value == 0) {
			alert('이메일을 입력해주세요');
			return;
		}

		submitJoinFormDone = true;
		form.submit();
	}
</script>
<section class="center">
		<div class="memberForm">
			<form action="../member/doJoin" method="POST" id="signUpForm" onsubmit="submitJoinForm(this); return false;">
				<h1>회원가입</h1>
				<div class="input_box flex fd-c flex-ai-c">
					<label for="loginId">
						<span>아이디</span>
					</label>
						<input type="text" name="loginId" placeholder="아이디를 입력하세요" id="loginId"/>
					<label for="loginPw">
						<span>비밀번호</span>
					</label>
						<input type="password" name="loginPw" placeholder="비밀번호를 입력하세요"  id="loginPw"/>
					<label for="loginPwConfirm">
						<span>비밀번호 확인</span>
					</label>
						<input type="password" name="loginPwConfirm" placeholder="비밀번호를 입력하세요"  id="loginPwConfirm"/>
					<label for="nickname">
						<span>닉네임</span>
					</label>
						<input type="text" name="nickname" placeholder="닉네임을 입력하세요"  id="nickname"/>
					<label for="email">
						<span>이메일</span>
					</label>
						<input type="email" name="email" placeholder="이메일을 입력하세요"  id="email"/>
					<button id="submit_btn" type="submit">가입</button>
				</div>
			</form>
		</div>
</section>
<%@ include file="../common/foot.jspf"%>