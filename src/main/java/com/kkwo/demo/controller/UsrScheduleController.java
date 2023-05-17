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
	
	public UsrScheduleController(ScheduleService ScheduleService) {
		this.ScheduleService = ScheduleService;
	}
	@RequestMapping("/usr/event/schedule")
	public String showSchedule(Model model) {
		List<Schedule> schedule = ScheduleService.getSchedule();
		model.addAttribute("schedule", schedule);
		return "usr/event/schedule";
	}
}