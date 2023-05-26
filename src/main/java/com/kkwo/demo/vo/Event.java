package com.kkwo.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	private int id;
	private String regDate;
	private String updateDate;
	private int genreId;
	private String location;
	private String title;
	private String detail;
	private int duration;
	
	private String extra__genreName;
}
