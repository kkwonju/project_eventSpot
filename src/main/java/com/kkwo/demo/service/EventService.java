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
	
	// 이벤트 서비스 클래스
	
	// EventRepository 객체를 주입받는 생성자
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	/** 마지막 입력된 id 가져오기 */
	private int getLastInsertId() {
		int id = eventRepository.getLastInsertId();
		return id;
	}

	/** 이벤트 목록 가져오기 */
	public List<Event> getEvents() {
		return eventRepository.getEvents();
	}
	
	/**
	 조건에 만족하는 이벤트 목록 가져오기
	 	1. searchKeyword ( 검색어와 부분 일치하는 )
	 */
	public List<Event> getForPrintEvents(String searchKeyword) {
		return eventRepository.getForPrintEvents(searchKeyword);
	}
	
	/** 이벤트 가져오기 */
	public Event getEvent(int id) {
		return eventRepository.getEvent(id);
	}
	
	/** 이벤트 추가 */
	public int addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {

		int affectedRow = eventRepository.addEvent(beginDt, endDt, genreId, location, title, detail, duration);
		
		if (affectedRow != 1) {
			return -1;
		}
		
		int eventId = getLastInsertId();
		return eventId;
	}

	/** 이벤트 삭제 */
	public int deleteEvent(int id) {
		int affectedRow = eventRepository.deleteEvent(id);
		
		if(affectedRow != 1) {
			return -1;
		}
		
		return affectedRow;
	}
	
	/** 이벤트 수정 */
	public int modifyEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {
		
		int affectedRow =  eventRepository.modifyEvent(id, beginDt, endDt, genreId, location, title, detail, duration);
		
		if(affectedRow != 1) {
			return -1;
		}
		return affectedRow;
	}

}
