package com.kkwo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;

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
	public Object addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {
		
		if(beginDt == null || beginDt.length() == 0) {
			return "시작 날짜를 입력해주세요";
		}
		if(endDt == null || endDt.length() == 0) {
			return "종료 날짜를 입력해주세요";
		}
		if(genreId == 0) {
			return "장르를 입력해주세요";
		}
		if(location == null || location.length() == 0) {
			return "장소를 입력해주세요";
		}
		if(title == null || title.length() == 0) {
			return "제목을 입력해주세요";
		}
		if(detail == null || detail.length() == 0) {
			return "내용을 입력해주세요";
		}
		if(duration == 0) {
			return "공연 시간을 입력해주세요";
		}
		
		int eventId = eventService.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
		if(eventId == -1) {
			return "이벤트 추가 실패";
		}
		return eventId;
	}

	@RequestMapping("/admin/event/updateEvent")
	@ResponseBody
	public String updateEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {
		int result = eventService.updateEvent(id, beginDt, endDt, genreId, location, title, detail, duration);
		if(result == -1) {
			return "업데이트 실패";
		}
		return "업데이트 성공";
	}

	@RequestMapping("/admin/event/deleteEvent")
	@ResponseBody
	public String deleteEvent(int id) {
		int result = eventService.deleteEvent(id);
		if(result == -1) {
			return "삭제 실패";
		}
		return "삭제 성공";
	}
}