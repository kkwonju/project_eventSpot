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
	
	public ScheduleService(ScheduleRepository scheduleRepository){
		this.scheduleRepository = scheduleRepository;
	}

	public List<Schedule> getScheduleList() {
		return scheduleRepository.getScheduleList();
	}

	public List<Schedule> getForPrintScheduleList(String searchKeyword) {
		return scheduleRepository.getForPrintScheduleList(searchKeyword);
	}
}
