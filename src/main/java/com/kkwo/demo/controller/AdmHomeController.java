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

	public AdmHomeController(EventService eventService, MemberService memberService, ScheduleService scheduleService) {
		this.eventService = eventService;
		this.memberService = memberService;
		this.scheduleService = scheduleService;
	}

	@RequestMapping("/admin/home/main")
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
		return "admin/home/main";
	}
}
