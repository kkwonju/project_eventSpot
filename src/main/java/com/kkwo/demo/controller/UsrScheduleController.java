package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkwo.demo.service.ScheduleService;
import com.kkwo.demo.vo.Schedule;

@Controller
public class UsrScheduleController {
	@Autowired
	private ScheduleService ScheduleService;
	
	// 사용자 스케쥴 컨트롤러 클래스
	
	// ScheduleService 객체를 주입받는 생성자
	public UsrScheduleController(ScheduleService ScheduleService) {
		this.ScheduleService = ScheduleService;
	}
	
	/**
	 * 스케쥴 리스트를 보여주는 메서드;
	 * 모든 스케쥴을 담은 리스트를 모델에 추가하고 사용자용 스케쥴 리스트 페이지를 반환한다
	 * */
	@RequestMapping("/usr/event/schedule")
	public String showSchedule(Model model) {
		List<Schedule> scheduleList = ScheduleService.getScheduleList();
		model.addAttribute("scheduleList", scheduleList);
		return "usr/event/schedule";
	}
}