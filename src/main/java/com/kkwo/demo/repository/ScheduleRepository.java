package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Schedule;

@Mapper
public interface ScheduleRepository {

	// SELECT
	List<Schedule> getScheduleList();

	// SELECT
	List<Schedule> getForPrintScheduleList(String searchKeyword);
	
}
