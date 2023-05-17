package com.kkwo.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
	private int id;
	private String regDate;
	private String updateDate;
	private int eventId;
	private String eventDate;
	private String startTime;
}