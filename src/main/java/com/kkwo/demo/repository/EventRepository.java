package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Event;

@Mapper
public interface EventRepository {

	// SELECT
	public List<Event> getEvents();

	// SELECT
	public List<Event> getForPrintEvents(String searchKeyword);
	
	// SELECT
	public Event getEventById(int id);

	// INSERT
	public int addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration);

	// DELETE
	public int deleteEvent(int id);

	// UPDATE
	public int modifyEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration);

	// SELECT
	public int getLastInsertId();

	
}
