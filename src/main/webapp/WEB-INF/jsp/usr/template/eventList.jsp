<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content_box">
	<div class="contents flex">
		<div class="location_box">event.location</div>
		<div class="img_box flex">
			<a class="detail_btn flex" href="javascript:popup(  event.id  );"
				onclick="getReplyList(  event.id  );">
				<img src="/resource/image/image_  event.imgId  .jpg" alt="image" />
			</a>
		</div>
		<div class="reaction_box">
			<button class="js_collect_btn" id="js_collect_btn_  index  ">
				<i class="unselected fa-regular fa-bookmark"></i> <i
					class="selected fa-solid fa-bookmark"></i>
			</button>
			1.2k
		</div>
		<div class="reply_box pl-5"></div>
	</div>
</div>
<div class="detail_bg" id="detail_bg_  event.id "></div>
<div class="detail_box" id="detail_  event.id  ">
	<div class="flex">
		<div class="dt_content_box">
			<div class="dt_img">
				<img src="/resource/image/image_  event.imgId  .jpg" alt="" />
			</div>
		</div>
		<div class="dt_reply_box flex fd-c">
			<a class="side_btn exit_btn">
				<i class="fa-solid fa-xmark"></i>
			</a>
			<a href="http://ticket.interpark.com/TiKi/Special/TPRegionReserve.asp?Region=42061&RegionName=%B4%EB%C0%FC"
				target="_blank" class="side_btn reservation_btn">예매</a>
			<div class="dt_location flex">event.location</div>
			<div class="dt_content_body">event.title</div>
			<div class="dt_reply" id="dt_reply_  event.id  "></div>
			<div class="dt_reply_edit">
				<form onsubmit="return false;">
					<div class="flex flex-ai-c">
						<input type="hidden" id="reply_relId_ event.id " name="relId"
							value="  event.id  " />
						<textarea autofocus autocomplete="off" id="reply_body_ event.id "
							name="body" style="resize: none;" placeholder="댓글달기"></textarea>
						<button onclick="writeReply( event.id );" type="button">게시</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>