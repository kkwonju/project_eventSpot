package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.ResultData;

import ch.qos.logback.core.joran.conditional.IfAction;

@Controller
public class AdmEventController {
	@Autowired
	private EventService eventService;

	// 관리자 이벤트 컨트롤러 클래스

	// EventService 객체를 주입받는 생성자
	public AdmEventController(EventService eventService) {
		this.eventService = eventService;
	}

	/**
	 * @return 관리자용 이벤트 추가 페이지 반환
	 */
	@RequestMapping("/admin/manage/showAddEvent")
	public String showAddEventForm() {
		return "admin/manage/addEvent";
	}

	/**
	 * 이벤트 등록 핸들러 메서드
	 * 
	 * TB_EVENT / 등록
	 * 
	 * @param beginDt  시작 날짜
	 * @param endDt    종료 날짜
	 * @param endDt    종료 날짜
	 * @param genreId  장르 ID
	 * @param location 장소
	 * @param title    제목
	 * @param detail   내용
	 * @param duration 소요시간
	 * 
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/admin/manage/addEvent")
	@ResponseBody
	public String addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration) {

		// 유효성 검사
		if (Ut.isEmpty(beginDt)) {
			return Ut.jsHistoryBack("시작 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(endDt)) {
			return Ut.jsHistoryBack("종료 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(genreId)) {
			return Ut.jsHistoryBack("장르를 입력해주세요");
		}
		if (Ut.isEmpty(location)) {
			return Ut.jsHistoryBack("장소를 입력해주세요");
		}
		if (Ut.isEmpty(title)) {
			return Ut.jsHistoryBack("제목을 입력해주세요");
		}
		if (Ut.isEmpty(detail)) {
			return Ut.jsHistoryBack("내용을 입력해주세요");
		}
		if (Ut.isEmpty(duration)) {
			return Ut.jsHistoryBack("진행 시간을 입력해주세요");
		}

		// 이벤트 추가를 시도하고 추가 결과에 따라 처리 ( 실패 : -1 )
		int eventId = eventService.addEvent(beginDt, endDt, genreId, location, title, detail, duration);

		if (eventId == -1) {
			return Ut.jsHistoryBack("이벤트 추가 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 이벤트를 추가했습니다", eventId), "/admin/manage/eventList");
	}

	/**
	 * TB_EVENT / 조회
	 * 
	 * @return 관리자용 이벤트 리스트 페이지 반환
	 */
	@RequestMapping("/admin/manage/eventList")
	public String showEventList(Model model) {

		List<Event> events = eventService.getEvents();

		model.addAttribute("events", events);

		return "admin/manage/eventList";
	}

	/**
	 * TB_EVENT / 조회
	 * 
	 * @param id 수정할 이벤트 ID;
	 * 
	 * @return 관리자용 이벤트 수정 페이지 반환
	 */
	@RequestMapping("/admin/manage/showModifyEvent")
	public String showModifyEventForm(Model model, int id) {

		Event event = eventService.getEvent(id);

		model.addAttribute("event", event);

		return "admin/manage/modifyEvent";
	}

	/**
	 * 이벤트 수정 핸들러 메서드
	 * 
	 * TB_EVENT / 수정
	 * 
	 * @param id       수정할 이벤트 ID
	 * @param beginDt  시작날짜
	 * @param endDt    종료날짜
	 * @param genreId  장르 id
	 * @param location 장소
	 * @param title    제목
	 * @param detail   내용
	 * @param duration 진행시간
	 * 
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/admin/manage/modifyEvent")
	@ResponseBody
	public String modifyEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration) {

		// 유효성 검사
		if (Ut.isEmpty(id)) {
			return Ut.jsHistoryBack("이벤트 번호를 입력해주세요");
		}
		if (Ut.isEmpty(beginDt)) {
			return Ut.jsHistoryBack("시작 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(endDt)) {
			return Ut.jsHistoryBack("종료 날짜를 입력해주세요");
		}
		if (Ut.isEmpty(genreId)) {
			return Ut.jsHistoryBack("장르를 입력해주세요");
		}
		if (Ut.isEmpty(location)) {
			return Ut.jsHistoryBack("장소를 입력해주세요");
		}
		if (Ut.isEmpty(title)) {
			return Ut.jsHistoryBack("제목을 입력해주세요");
		}
		if (Ut.isEmpty(detail)) {
			return Ut.jsHistoryBack("내용을 입력해주세요");
		}
		if (Ut.isEmpty(duration)) {
			return Ut.jsHistoryBack("공연 시간을 입력해주세요");
		}

		// 이벤트 수정을 시도하고 수정 결과에 따라 처리 ( 실패 : -1 )
		int result = eventService.modifyEvent(id, beginDt, endDt, genreId, location, title, detail, duration);

		if (result == -1) {
			return Ut.jsHistoryBack("이벤트 수정 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 이벤트 수정 성공", id), "/admin/manage/eventList");
	}

	/**
	 * 이벤트 삭제 핸들러 메서드
	 * 
	 * TB_EVENT / 삭제
	 *
	 * @param id 삭제할 이벤트 ID
	 * 
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/admin/manage/deleteEvent")
	@ResponseBody
	public String deleteEvent(int id) {
		int result = eventService.deleteEvent(id);

		// 이벤트 삭제를 시도하고 삭제 결과에 따라 처리 ( 실패 : -1 )
		if (result == -1) {
			return Ut.jsHistoryBack("이벤트 삭제 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 이벤트 삭제 성공", id), "/admin/manage/eventList");
	}
}