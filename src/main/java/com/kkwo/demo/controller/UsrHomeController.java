package com.kkwo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
	
	// 사용자 홈 컨트롤러 클래스
	
	/**
	 * 메인 화면을 보여주는 메서드;
	 * 주소 입력이 없을 때 사용자 이벤트 리스트 페이지를 반환한다
	 * */
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/event/list";
	}
}
