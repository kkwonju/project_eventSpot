package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Event;

@Mapper
public interface EventRepository {

	public List<Event> getEvents();

	public List<Event> getForPrintEvents(String searchKeyword);
	
	public Event getEvent(int id);

	public int addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration);

	public int deleteEvent(int id);

	public int updateEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration);

	public int getLastInsertId();

	
}
