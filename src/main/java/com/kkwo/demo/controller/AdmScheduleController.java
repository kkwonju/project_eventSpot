package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkwo.demo.service.ScheduleService;
import com.kkwo.demo.vo.Schedule;

@Controller
public class AdmScheduleController {
	@Autowired
	private ScheduleService ScheduleService;

	// 관리자 스케쥴 컨트롤러 클래스

	// ScheduleService 객체를 주입받는 생성자
	public AdmScheduleController(ScheduleService ScheduleService) {
		this.ScheduleService = ScheduleService;
	}

	/**
	 * TB_SCHEDULE / 조회
	 * 
	 * @return 관리자용 스케쥴 리스트 페이지 반환
	 */
	@RequestMapping("/admin/manage/schedule")
	public String showScheduleList(Model model) {
		List<Schedule> scheduleList = ScheduleService.getScheduleList();
		model.addAttribute("scheduleList", scheduleList);
		return "admin/manage/schedule";
	}
}