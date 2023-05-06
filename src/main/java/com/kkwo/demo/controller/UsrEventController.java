package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.vo.Event;

@Controller
public class UsrEventController {
	@Autowired
	private EventService eventService;
	
	public UsrEventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping("/usr/event/showList")
	@ResponseBody
	public List<Event> showList() {
		List<Event> events = eventService.getEvents();
		return events;
	}
	
	@RequestMapping("/usr/event/showEvent")
	@ResponseBody
	public Event showEvent(int id) {
		Event event = eventService.getEvent(id);
		return event;
	}
}