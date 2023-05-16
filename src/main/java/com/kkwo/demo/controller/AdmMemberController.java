package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.MemberService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Event;
import com.kkwo.demo.vo.Member;

@Controller
public class AdmMemberController {
	@Autowired
	private MemberService memberService;

	public AdmMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/admin/manage/memberList")
	public String showEventList(Model model) {
		List<Member> members = memberService.getMembers();
		model.addAttribute("members", members);
		return "admin/manage/memberList";
	}

	@RequestMapping("/admin/manage/deleteMember")
	@ResponseBody
	public String deleteEvent(int id) {
		int result = memberService.deleteMember(id);
		
		if (result == -1) {
			return Ut.jsHistoryBack("회원 탈퇴 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 회원 탈퇴 성공", id), "/admin/manage/memberList");
	}
}