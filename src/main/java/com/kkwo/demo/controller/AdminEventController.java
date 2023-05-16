package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.ResultData;

import ch.qos.logback.core.joran.conditional.IfAction;

@Controller
public class AdminEventController {
	@Autowired
	private EventService eventService;

	public AdminEventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping("/admin/manage/showAddEvent")
	public String showAddEventForm() {
		return "admin/manage/addEvent";
	}
	
	@RequestMapping("/admin/manage/addEvent")
	@ResponseBody
	public String addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {

		if (Ut.isEmpty(beginDt)) {
			return Ut.jsHistoryBack("시작 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(endDt)) {
			return Ut.jsHistoryBack("종료 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(genreId)) {
			return Ut.jsHistoryBack("장르를 입력해주세요");
		}
		if (Ut.isEmpty(location)) {
			return Ut.jsHistoryBack("장소를 입력해주세요");
		}
		if (Ut.isEmpty(title)) {
			return Ut.jsHistoryBack("제목을 입력해주세요");
		}
		if (Ut.isEmpty(detail)) {
			return Ut.jsHistoryBack("내용을 입력해주세요");
		}
		if (Ut.isEmpty(duration)) {
			return Ut.jsHistoryBack("진행 시간을 입력해주세요");
		}

		int eventId = eventService.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
		if (eventId == -1) {
			return Ut.jsHistoryBack("이벤트 추가 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 이벤트를 추가했습니다" , eventId), "/admin/manage/eventList");
	}
	
	@RequestMapping("/admin/manage/eventList")
	public String showEventList(Model model) {
		List<Event> events = eventService.getEvents();
		model.addAttribute("events", events);
		return "admin/manage/eventList";
	}
	
	@RequestMapping("/admin/manage/showUpdateEvent")
	public String showUpdateEventForm(Model model, int id) {
		Event event = eventService.getEvent(id);
		model.addAttribute("event", event);
		return "admin/manage/updateEvent";
	}
	
	@RequestMapping("/admin/event/updateEvent")
	@ResponseBody
	public String updateEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {
		
		if (Ut.isEmpty(id)) {
			return Ut.jsHistoryBack("이벤트 번호를 입력해주세요");
		}
		if (Ut.isEmpty(beginDt)) {
			return Ut.jsHistoryBack("시작 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(endDt)) {
			return Ut.jsHistoryBack("종료 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(genreId)) {
			return Ut.jsHistoryBack("장르를 입력해주세요");
		}
		if (Ut.isEmpty(location)) {
			return Ut.jsHistoryBack("장소를 입력해주세요");
		}
		if (Ut.isEmpty(title)) {
			return Ut.jsHistoryBack("제목을 입력해주세요");
		}
		if (Ut.isEmpty(detail)) {
			return Ut.jsHistoryBack("내용을 입력해주세요");
		}
		if (Ut.isEmpty(duration)) {
			return Ut.jsHistoryBack("공연 시간을 입력해주세요");
		}
		
		int result = eventService.updateEvent(id, beginDt, endDt, genreId, location, title, detail, duration);
		
		if (result == -1) {
			return Ut.jsHistoryBack("이벤트 업데이트 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 이벤트 업데이트 성공", id), "/");
	}

	@RequestMapping("/admin/event/deleteEvent")
	@ResponseBody
	public String deleteEvent(int id) {
		
		int result = eventService.deleteEvent(id);
		
		if (result == -1) {
			return Ut.jsHistoryBack("이벤트 삭제 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 이벤트 삭제 성공", id), "/");
	}
}