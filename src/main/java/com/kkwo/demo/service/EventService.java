package com.kkwo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.EventRepository;
import com.kkwo.demo.vo.Event;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;

	public List<Event> getEvents() {
		return eventRepository.getEvents();
	}

	public void addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail, int duration) {
		eventRepository.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
	}
	
}
