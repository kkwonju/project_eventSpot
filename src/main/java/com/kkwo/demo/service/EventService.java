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

	public Event getEvent(int id) {
		return eventRepository.getEvent(id);
	}

	public void addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {
		eventRepository.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
	}

	public void deleteEvent(int id) {
		eventRepository.deleteEvent(id);
	}

	public void updateEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {
		eventRepository.updateEvent(id, beginDt, endDt, genreId, location, title, detail, duration);
	}

}
