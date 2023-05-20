package com.kkwo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.ScheduleRepository;
import com.kkwo.demo.vo.Schedule;

@Service
public class ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	// 스케쥴 서비스 클래스
	
	// ScheduleRepository 객체를 주입받는 생성자
	public ScheduleService(ScheduleRepository scheduleRepository){
		this.scheduleRepository = scheduleRepository;
	}
	
	/**
	 * TB_SCHEDULE / 조회
	 * 
	 * @return 스케쥴 리스트
	 * */
	public List<Schedule> getScheduleList() {
		return scheduleRepository.getScheduleList();
	}
	
	/**
	 * TB_SCHEDULE / 조회
	 * 
	 * @return 검색된 스케쥴 리스트
	 * */
	public List<Schedule> getForPrintScheduleList(String searchKeyword) {
		return scheduleRepository.getForPrintScheduleList(searchKeyword);
	}
}
