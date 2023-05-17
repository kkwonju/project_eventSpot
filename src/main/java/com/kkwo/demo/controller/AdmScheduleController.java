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
	
	public AdmScheduleController(ScheduleService ScheduleService) {
		this.ScheduleService = ScheduleService;
	}
	
	@RequestMapping("/admin/manage/schedule")
	public String showSchedule(Model model) {
		List<Schedule> scheduleList = ScheduleService.getSchedule();
		model.addAttribute("scheduleList", scheduleList);
		return "admin/manage/schedule";
	}
}