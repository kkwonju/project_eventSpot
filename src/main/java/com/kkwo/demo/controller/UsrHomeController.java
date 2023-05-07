package com.kkwo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/event/list";
	}
}
