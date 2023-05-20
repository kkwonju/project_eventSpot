package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.ResultData;
import com.kkwo.demo.vo.Rq;

import ch.qos.logback.core.joran.conditional.IfAction;

@Controller
public class UsrEventController {
	@Autowired
	private EventService eventService;

	// 사용자 이벤트 컨트롤러 클래스

	// EventService 객체를 주입받는 생성자
	public UsrEventController(EventService eventService) {
		this.eventService = eventService;
	}

	/**
	 * TB_EVENT / 조회
	 * 
	 * @param searchKeyword 검색어 (기본값 : "")
	 * 
	 * @return 사용자용 이벤트 리스트 페이지 반환
	 */
	@RequestMapping("/usr/event/list")
	public String showEventlist(Model model, @RequestParam(defaultValue = "") String searchKeyword) {
		List<Event> events = eventService.getForPrintEvents(searchKeyword);
		int eventsCnt = events.size();
		model.addAttribute("events", events);
		model.addAttribute("eventsCnt", eventsCnt);
		return "usr/event/list";
	}

//	@RequestMapping("/usr/event/detail")
//	public String showEventDetail(Model model, int id) {
//		if(Ut.isEmpty(id)) {
//			return Ut.jsHistoryBack("id를 입력해주세요");
//		}
//		Event event = eventService.getEvent(id);
//		if(event == null) {
//			return Ut.jsHistoryBack(Ut.f("%d번 이벤트는 없습니다", id));
//		}
//		model.addAttribute("event", event);
//		return "usr/event/detail";
//	}
}