package com.kkwo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;

@Controller
public class AdminEventController {
	@Autowired
	private EventService eventService;
	
	public AdminEventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping("/admin/event/addEvent")
	@ResponseBody
	public void addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail, int duration) {
		eventService.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
	}
}