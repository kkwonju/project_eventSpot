package com.kkwo.demo.controller;

import java.util.List;

import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.service.ReplyService;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.Reply;
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
	 * @param searchKeyword 검색어 (기본값 : "")
	 * 
	 * @return 사용자용 이벤트 리스트 페이지 반환
	 */
	@RequestMapping("/usr/event/list")
	public String showEventlist(Model model, @RequestParam(defaultValue = "") String searchKeyword) {
		List<Event> events = eventService.getForPrintEvents(searchKeyword);
		List<Reply> replyList = replyService.getReplies();
		int eventsCnt = events.size();
		int repliesCnt = replyList.size();
		model.addAttribute("events", events);
		model.addAttribute("replyList", replyList);
		model.addAttribute("eventsCnt", eventsCnt);
		model.addAttribute("repliesCnt", repliesCnt);
		return "usr/event/list";
	}
}