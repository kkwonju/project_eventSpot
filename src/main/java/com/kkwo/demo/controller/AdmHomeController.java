package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.service.MemberService;
import com.kkwo.demo.service.ScheduleService;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.Member;
import com.kkwo.demo.vo.Schedule;

@Controller
public class AdmHomeController {
	@Autowired
	private EventService eventService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ScheduleService scheduleService;

	// 관리자 메인 컨트롤러 클래스

	// Event, Member, Schedule의 각 Service 객체를 주입받는 생성자
	public AdmHomeController(EventService eventService, MemberService memberService, ScheduleService scheduleService) {
		this.eventService = eventService;
		this.memberService = memberService;
		this.scheduleService = scheduleService;
	}

	/**
	 * TB_EVENT, TB_MEMBER, TB_SCHEDULE / 조회
	 * 
	 * @param searchKeyword 검색어 (기본값 = "");
	 * 
	 * @return (검색어) 있을 때 : 검색창과 조회된 리스트 반환, 없을 때 : 검색창만 반환
	 */
	@RequestMapping("/admin/home/search")
	public String showResultsFound(Model model, @RequestParam(defaultValue = "") String searchKeyword) {
		List<Event> events = eventService.getForPrintEvents(searchKeyword);
		List<Member> members = memberService.getForPrintMembers(searchKeyword);
		List<Schedule> scheduleList = scheduleService.getForPrintScheduleList(searchKeyword);
		int eventsCnt = events.size();
		int membersCnt = members.size();
		int scheduleListCnt = scheduleList.size();

		model.addAttribute("events", events);
		model.addAttribute("members", members);
		model.addAttribute("scheduleList", scheduleList);
		model.addAttribute("eventsCnt", eventsCnt);
		model.addAttribute("membersCnt", membersCnt);
		model.addAttribute("scheduleListCnt", scheduleListCnt);
		return "admin/home/search";
	}
}
