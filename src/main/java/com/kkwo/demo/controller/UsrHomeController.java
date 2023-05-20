package com.kkwo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {

	// 사용자 홈 컨트롤러 클래스

	/**
	 * 메인 페이지
	 * 
	 * @return 사용자용 이벤트 리스트 페이지 반환
	 */
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/event/list";
	}
}
