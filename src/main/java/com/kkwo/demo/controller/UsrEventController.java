package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String list() {
		return "usr/event/list";
	}
	
	/** 이벤트 리스트 보여주기 */
	@RequestMapping("/usr/event/showList")
	@ResponseBody
	public ResultData showList() {
		List<Event> events = eventService.getEvents();
		if(events == null || events.size() == 0) {
			return ResultData.buildResultData("F-S", "이벤트 목록이 없습니다");
		}
		return ResultData.buildResultData("S-1", "이벤트 목록입니다", "events", events);
	}
	
	/** 이벤트 보여주기 */
	@RequestMapping("/usr/event/showEvent")
	@ResponseBody
	public ResultData showEvent(int id) {
		if(Ut.isEmpty(id)) {
			return ResultData.buildResultData("F-N", "id를 입력해주세요");
		}
		Event event = eventService.getEvent(id);
		if(event == null) {
			return ResultData.buildResultData("F-S", Ut.f("%d번 이벤트는 없습니다", id), "id", id);
		}
		return ResultData.buildResultData("F-S", Ut.f("%d번 이벤트", id), "event", event);
	}
}