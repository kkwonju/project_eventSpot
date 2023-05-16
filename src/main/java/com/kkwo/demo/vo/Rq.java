package com.kkwo.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kkwo.demo.service.MemberService;

import lombok.Getter;

/* 사용자의 요청과 응답 처리, session 관리 */

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;

	private HttpServletRequest req;
	private HttpSession session;

	public Rq(HttpServletRequest req, MemberService memberService) {
		this.req = req;
		this.session = req.getSession();
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;
		
		
		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}
		
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;;
	}
	
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}
	
	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
	
	public String jsHistoryBackOnView(String resultMsg) {
		req.setAttribute("resultMsg", resultMsg);
		req.setAttribute("historyBack", true);
		return "usr/common/js";
	}
	
	public String jsReplaceOnView(String resultMsg, String replaceUri) {
		req.setAttribute("resultMsg", resultMsg);
		req.setAttribute("replaceUri", replaceUri);
		return "usr/common/js";
	}
}
