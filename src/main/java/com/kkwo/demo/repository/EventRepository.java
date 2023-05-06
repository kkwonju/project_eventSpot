package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kkwo.demo.vo.Event;

@Mapper
public interface EventRepository {

	public List<Event> getEvents();

	public Event getEvent(int id);

	public void addEvent(String beginDt, String endDt, int genreId, String location, String title, String detail,
			int duration);

	public void deleteEvent(int id);

	public void updateEvent(int id, String beginDt, String endDt, int genreId, String location, String title,
			String detail, int duration);
}
