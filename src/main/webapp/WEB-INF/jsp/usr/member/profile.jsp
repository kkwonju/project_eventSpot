<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<section class="center">
	<div class="profile_box">
		<div class="id_card flex">
			<div class="profile_img">
				<div class="img"></div>
			</div>
			<div class="profile_info">
				<div class="nickname">${member.nickname}</div>
				<div>${member.loginId }</div>
				<div>${member.regDate.substring(2,10)}</div>
				<div>${member.email}</div>
			</div>
		</div>
		<div class="bhd_idCard"><a href="../member/modify">MODIFY > </a></div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>