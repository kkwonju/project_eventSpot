package com.kkwo.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int eventId;
}
