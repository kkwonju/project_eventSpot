<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>
<style>
	.addEventForm_box {
		width: 100%;
		margin-top: 20px;
	}
	
	.addEventForm_box > h1 {
		text-align: center;
	}
	

	.addEventForm {
		position: relative;
		border: 2px solid black;
		width: 700px;
		margin: 0 auto;
	}
	
	.addEventForm_box button {
		position: absolute;
		bottom: -70px;
		left: calc(50% - 200px);
		width: 400px;
		height: 50px;
		background-color: none;
		border-radius: 10px;
	}
	
	.info_box > input, .info_box > select {
		height: 30px;
	}
	
	.info_box > label {
		padding-left: 5px;
		font-size: 1.1rem;
		margin-top: 10px;
	}
	
	.info_box {
		width: 390px;
	}
	
	.info_box > textarea {
		resize: none;
		width: 99%;
		height: 50px;
	}
	
	.addEventForm > .image_box {
		justify-content: space-between;
		border-left: 1px solid black;
	}
	
	.addEventForm > .image_box > .preview {
		padding-left: 5px;
		width: 300px;
	}
	
	.addEventForm > .image_box > .preview > img {
		max-width: 100%;
	}
	
	.addEventForm > .image_box > .input_img > label {
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 1.2rem;
		width: 100%;
		height: 100%;
	}
	
	.addEventForm > .image_box > .input_img > input {
		visibility: hidden;
	}
	
</style>
<section class="addEventForm_box">
	<h1>이벤트 수정</h1>
	<form action="/admin/manage/modifyEvent" method="post">
		<div class="addEventForm flex">
			<div class="info_box flex fd-c">
				<input type="hidden" name="id" value="${event.id}"/>
				<label for="beginDt">시작 날짜</label>
				<input required name="beginDt" type="date" value="${event.beginDt}"/>
				<label for="endDt">종료 날짜</label>
				<input required name="endDt" type="date" value="${event.endDt}"/>
				<label for="location">장소</label>
				<input required name="location" type="text" value="${event.location}"/>
				
				<label for="select_genre">장르</label>
				<select required data-value="${event.genreId}" id="select_genre" name="genreId">
				  <option disabled selected value="">장르</option>
				  <option value="1001">기타</option>
				  <option value="1002">뮤지컬</option>
				  <option value="1003">콘서트</option>
				  <option value="1004">연극</option>
				  <option value="1005">클래식/무용</option>
				  <option value="1006">전시/행사</option>
				  <option value="1007">레저</option>
				</select>
				
				<label for="title">제목</label>
				<input required name="title" type="text" value="${event.title}"/>
				<label for="detail">내용</label>
				<textarea name="detail" id="detail">${event.detail}</textarea>
				<label for="duration">진행 시간(분)</label>
				<input required name="duration" type="text" value="${event.duration}"/>
			</div>
			<div class="image_box flex fd-c">
				<div class="input_img">
					<label for="image_input">Click here &nbsp;<i class="fa-regular fa-image" style="color: #000000;"></i></label>
					<input name="eventImg" type="file" accept="image/*" id="image_input"/>
				</div>
				<div class="preview">
					<img id="preview_img" src="#" alt="" />
					FileName : 
					<span id="fileName"></span>
				</div>
			</div>
			<button type="submit">등록</button>
		</div>
	</form>
</section>

<script>

</script>

<%@ include file="../common/foot.jspf"%>