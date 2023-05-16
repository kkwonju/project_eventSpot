package com.kkwo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {
	
	@RequestMapping("/admin/home/main")
	public String showRoot() {
		return "admin/home/main";
	}
}
