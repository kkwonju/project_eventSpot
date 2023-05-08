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
public class UsrEventController {
	@Autowired
	private EventService eventService;
	
	public UsrEventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	/** 메인페이지, 리스트 */
	@RequestMapping("/usr/event/list")
	public String showEventlist(Model model) {
		List<Event> events = eventService.getEvents();
		model.addAttribute("events", events);
		return "usr/event/list";
	}
	
	/** 이벤트 보여주기 */
	@RequestMapping("/usr/event/detail")
	public String showEventDetail(Model model, int id) {
		if(Ut.isEmpty(id)) {
			return Ut.jsHistoryBack("id를 입력해주세요");
		}
		Event event = eventService.getEvent(id);
		if(event == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 이벤트는 없습니다", id));
		}
		model.addAttribute("event", event);
		return "usr/event/detail";
	}
}