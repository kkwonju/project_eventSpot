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
	
	private int getLastInsertId() {
		int id = eventRepository.getLastInsertId();
		return id;
	}

	public List<Event> getEvents() {
		return eventRepository.getEvents();
	}

	public Event getEvent(int id) {
		return eventRepository.getEvent(id);
	}

	public int addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {

		int affectedRow = eventRepository.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
		
		if (affectedRow == 0) {
			return -1;
		}
		
		int eventId = getLastInsertId();		
		return eventId;
	}

	public int deleteEvent(int id) {
		int affectedRow = eventRepository.deleteEvent(id);
		
		if(affectedRow == 0) {
			return -1;
		}
		
		return affectedRow;
	}

	public int updateEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {
		
		int affectedRow =  eventRepository.updateEvent(id, beginDt, endDt, genreId, location, title, detail, duration);
		
		if(affectedRow == 0) {
			return -1;
		}
		return affectedRow;
	}

}
