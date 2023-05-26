package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkwo.demo.service.BookmarkService;
import com.kkwo.demo.vo.Bookmark;
import com.kkwo.demo.vo.Rq;

@Controller
public class UsrBookmarkController {
	@Autowired
	private BookmarkService bookmarkService;
	@Autowired
	private Rq rq;
	
	public UsrBookmarkController(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}
	
	@RequestMapping("/usr/bookmark/list")
	public String showCollectionList(Model model) {
		List<Bookmark> bookmarkList = bookmarkService.getBookmarkList(rq.getLoginedMemberId());
		model.addAttribute("bookmarkList", bookmarkList);
		
		return "usr/bookmark/list";
	}
}