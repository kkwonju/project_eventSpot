<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<script type="text/javascript">
	let submitModifyFormDone = false;

	function submitModifyForm(form) {
		if (submitModifyFormDone) {
			alert('처리중입니다');
			return;
		}

		if (form.loginPwConfirm.value != form.loginPw.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.focus();
			return;
		}

		submitJoinFormDone = true;
		form.submit();
	}
</script>

<section class="center">
	<div class="profile_box">
		<div class="none flex">
			<div class="profile_img">
				<div class="img"></div>
			</div>
			<div class="profile_modify">
				<form action="../member/doModify" method="POST" onsubmit="submitModifyForm(this); return false;">
				 	<div class="flex fd-c flex-jc-sb">
						<label for="nickname">닉네임</label>
						<input type="text" id="nickname" name="nickname" value="${rq.loginedMember.nickname}" autofocus autocomplete="off" />
						<label for="loginPw">새 비밀번호</label>
						<input type="password" id="loginPw" name="loginPw" placeholder="새 비밀번호를 입력해주세요"/>
						<label for="loginpwConfirm">새 비밀번호 확인</label>
						<input type="password" id="loginpwConfirm" name="loginPwConfirm" placeholder="새 비밀번호를 입력해주세요"/>
						<button class="submit_btn up" type="submit">MODIFY</button>
				 	</div>
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>