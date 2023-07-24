package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.service.ReplyService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.ResultData;
import com.kkwo.demo.vo.Rq;

@Controller
public class UsrEventController {
	@Autowired
	private Rq rq;
	@Autowired
	private EventService eventService;
	@Autowired
	private ReplyService replyService;

	// 사용자 이벤트 컨트롤러 클래스

	// EventService 객체를 주입받는 생성자
	public UsrEventController(EventService eventService, ReplyService replyService) {
		this.eventService = eventService;
		this.replyService = replyService;
	}

	/** 
	 * TB_EVENT / 조회
	 * 
	 * @return 사용자용 이벤트 리스트 페이지 반환
	 */
	@RequestMapping("/usr/event/list")
	public String showEventlist2(Model model) {
		List<Event> events = eventService.getEvents();
		model.addAttribute("events", events);
		return "usr/event/list";
	}

	@RequestMapping("/usr/event/foundList")
	public String showFoundList(Model model, @RequestParam(defaultValue = "") String searchKeyword) {
		if (Ut.isEmpty(searchKeyword)) {
			return "redirect:/usr/event/list";
		}
		List<Event> events = eventService.getForPrintEvents(searchKeyword);
		int eventsCnt = events.size();
		model.addAttribute("events", events);
		model.addAttribute("eventsCnt", eventsCnt);
		return "usr/event/foundList";
	}

	@RequestMapping("/usr/event/getEventList")
	@ResponseBody
	public ResultData getEventList(int offset) {
		int limit = 4;
		List<Event> events = eventService.getEvents2(offset, limit);
		int loginedMemberId = rq.getLoginedMemberId();
		ResultData Rd = ResultData.buildResultData("S-1", "", "events", events);
		Rd.setData2("loginedMemberId", loginedMemberId);
		return Rd;
	}
	
	@RequestMapping("/usr/event/kakaoAPI")
	public String showMap() {
		return "usr/event/map";
	}
	
	@RequestMapping("/usr/event/getLocation")
	@ResponseBody
	public ResultData getLocation(int eventId) {
		Event event = eventService.getEventById(eventId);
		ResultData Rd = ResultData.buildResultData("S-1", "성공", "event", event);
		return Rd;
		
	}
}