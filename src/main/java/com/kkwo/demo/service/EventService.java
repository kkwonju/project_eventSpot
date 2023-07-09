package com.kkwo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.EventRepository;
import com.kkwo.demo.vo.Bookmark;
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

	/**
	 * @return 마지막으로 입력된 id
	 */
	private int getLastInsertId() {
		int id = eventRepository.getLastInsertId();
		return id;
	}

	/**
	 * TB_EVENT / 조회
	 * 
	 * @return 이벤트 리스트
	 */
	public List<Event> getEvents() {
		return eventRepository.getEvents();
	}

	/**
	 * TB_EVENT / 조회
	 * 
	 * @param searchKeyword 검색어
	 * 
	 * @return 출력용 이벤트 리스트
	 */
	public List<Event> getForPrintEvents(String searchKeyword) {
		return eventRepository.getForPrintEvents(searchKeyword);
	}

	/**
	 * @param id 조회할 이벤트 ID
	 * 
	 * @return Event 객체
	 */
	public Event getEventById(int id) {
		return eventRepository.getEventById(id);
	}

	/**
	 * 이벤트 추가 메서드
	 * 
	 * @param beginDt  시작날짜
	 * @param endDt    종료날짜
	 * @param genreId  장르 ID
	 * @param location 장소
	 * @param title    제목
	 * @param detail   내용
	 * @param duration 진행시간
	 * 
	 * @return 성공 : event.id; 실패 : -1;
	 */
	public int addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {

		int affectedRow = eventRepository.addEvent(beginDt, endDt, genreId, location, title, detail, duration);

		if (affectedRow != 1) {
			return -1;
		}

		int eventId = getLastInsertId();
		return eventId;
	}

	/**
	 * 이벤트 삭제 메서드
	 * 
	 * TB_EVENT / 삭제
	 * 
	 * @param id 삭제할 이벤트 ID
	 * 
	 * @return 성공 : 적용된 열의 갯수; 실패 : -1
	 * 
	 */
	public int deleteEvent(int id) {
		int affectedRow = eventRepository.deleteEvent(id);

		if (affectedRow != 1) {
			return -1;
		}

		return affectedRow;
	}

	/**
	 * 이벤트 수정 메서드
	 * 
	 * TB_EVENT / 수정
	 * 
	 * @param id       수정할 이벤트 id
	 * @param beginDt  시작날짜
	 * @param endDt    종료날짜
	 * @param genreId  장르 ID
	 * @param location 장소
	 * @param title    제목
	 * @param detail   내용
	 * @param duration 진행시간
	 * 
	 * @return 성공 : 적용된 열의 갯수; 실패 : -1
	 */
	public int modifyEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {

		int affectedRow = eventRepository.modifyEvent(id, beginDt, endDt, genreId, location, title, detail, duration);

		if (affectedRow != 1) {
			return -1;
		}
		return affectedRow;
	}

	public List<Event> getEvents2(int offset, int limit) {
		return eventRepository.getEvents2(offset, limit);
	}

	public List<Event> getBookmarkList(int actorId) {
		return eventRepository.getBookmarkList(actorId);
	}

}
