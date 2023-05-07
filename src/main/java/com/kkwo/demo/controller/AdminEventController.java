package com.kkwo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.ResultData;

import ch.qos.logback.core.joran.conditional.IfAction;

@Controller
public class AdminEventController {
	@Autowired
	private EventService eventService;

	public AdminEventController(EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping("/admin/event/addEvent")
	@ResponseBody
	public ResultData addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {

		if (Ut.isEmpty(beginDt)) {
			return ResultData.buildResultData("F-N", "시작 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(endDt)) {
			return ResultData.buildResultData("F-N", "종료 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(genreId)) {
			return ResultData.buildResultData("F-N", "장르를 입력해주세요");
		}
		if (Ut.isEmpty(location)) {
			return ResultData.buildResultData("F-N", "장소를 입력해주세요");
		}
		if (Ut.isEmpty(title)) {
			return ResultData.buildResultData("F-N", "제목을 입력해주세요");
		}
		if (Ut.isEmpty(detail)) {
			return ResultData.buildResultData("F-N", "내용을 입력해주세요");
		}
		if (Ut.isEmpty(duration)) {
			return ResultData.buildResultData("F-N", "공연 시간을 입력해주세요");
		}

		int eventId = eventService.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
		if (eventId == -1) {
			return ResultData.buildResultData("F-I", "이벤트 추가 실패");
		}
		return ResultData.buildResultData("S-1", Ut.f("%d번 이벤트 추가", eventId), "eventId", eventId);
	}

	@RequestMapping("/admin/event/updateEvent")
	@ResponseBody
	public ResultData updateEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {
		
		int result = eventService.updateEvent(id, beginDt, endDt, genreId, location, title, detail, duration);
		
		if (result == -1) {
			return ResultData.buildResultData("F-U", "이벤트 업데이트 실패");
		}
		return ResultData.buildResultData("S-1", Ut.f("%d번 이벤트 업데이트 성공", id), "id", id);
	}

	@RequestMapping("/admin/event/deleteEvent")
	@ResponseBody
	public ResultData deleteEvent(int id) {
		
		int result = eventService.deleteEvent(id);
		
		if (result == -1) {
			return ResultData.buildResultData("F-D", "이벤트 삭제 실패");
		}
		return ResultData.buildResultData("S-1", Ut.f("%d번 이벤트 삭제 성공", id), "id", id);
	}
}