package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.BookmarkService;
import com.kkwo.demo.service.EventService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.ResultData;
import com.kkwo.demo.vo.Rq;

@Controller
public class UsrBookmarkController {
	@Autowired
	private BookmarkService bookmarkService;
	@Autowired
	private EventService eventService;
	@Autowired
	private Rq rq;

	public UsrBookmarkController(BookmarkService bookmarkService, EventService eventService) {
		this.bookmarkService = bookmarkService;
		this.eventService = eventService;
	}

	@RequestMapping("/usr/bookmark/list")
	public String showCollectionList(Model model) {
		List<Event> events = eventService.getBookmarkList(rq.getLoginedMemberId());
		model.addAttribute("events", events);

		return "usr/bookmark/list";
	}

	@RequestMapping("/usr/bookmark/setBookmark")
	@ResponseBody
	public String setBookmark(int relId) {
		boolean actorCanMakeBookmark = bookmarkService.actorCanMakeBookmark(rq.getLoginedMemberId(), relId);
		if (actorCanMakeBookmark == false) {
			return Ut.jsHistoryBack("실패");
		}

		ResultData rd = bookmarkService.setBookmark(rq.getLoginedMemberId(), relId);

		if (rd.isFail()) {
			Ut.jsHistoryBack("실패");
		}

		return Ut.jsReplace("Bookmark!", "/");
	}
	
	@RequestMapping("/usr/bookmark/unsetBookmark")
	@ResponseBody
	public String unsetBookmark(int relId) {
		boolean actorCanMakeBookmark = bookmarkService.actorCanMakeBookmark(rq.getLoginedMemberId(), relId);
		if (actorCanMakeBookmark == false) {
			return Ut.jsHistoryBack("실패");
		}
		
		ResultData rd = bookmarkService.unsetBookmark(rq.getLoginedMemberId(), relId);
		
		if (rd.isFail()) {
			Ut.jsHistoryBack("실패");
		}
		
		return Ut.jsReplace("remove", "/");
	}
}