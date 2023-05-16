package com.kkwo.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.MemberService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Member;
import com.kkwo.demo.vo.ResultData;
import com.kkwo.demo.vo.Rq;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private Rq rq;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/join")
	public String showJoinForm() {
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String nickname, String email) {

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그인 중입니다");
		}
		if (Ut.isEmpty(loginId)) {
			return Ut.jsHistoryBack("로그인 아이디를 입력하세요");
		}
		if (Ut.isEmpty(loginPw)) {
			return Ut.jsHistoryBack("로그인 패스워드를 입력하세요");
		}
		if (Ut.isEmpty(nickname)) {
			return Ut.jsHistoryBack("닉네임을 입력하세요");
		}
		if (Ut.isEmpty(email)) {
			return Ut.jsHistoryBack("이메일를 입력하세요");
		}

		ResultData joinMemberRd = memberService.doJoinMember(loginId, loginPw, nickname, email);

		if (joinMemberRd.isFail()) {
			return Ut.jsHistoryBack(joinMemberRd.getResultMsg());
		}

		Member member = memberService.getMemberByLoginId(loginId);

		return Ut.jsReplace(Ut.f("%s님 회원가입 성공", member.getNickname()), "/");
	}

	@RequestMapping("/usr/member/login")
	public String showLoginForm() {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그인 중입니다");
		}

		if (Ut.isEmpty(loginId)) {
			return Ut.jsHistoryBack("로그인 아이디를 입력하세요");
		}
		if (Ut.isEmpty(loginPw)) {
			return Ut.jsHistoryBack("로그인 패스워드를 입력하세요");
		}

		ResultData loginMemberRd = memberService.doLoginMember(loginId, loginPw);

		if (loginMemberRd.isFail()) {
			return Ut.jsHistoryBack(loginMemberRd.getResultMsg());
		}

		Member member = memberService.getMemberByLoginId(loginId);
		
		rq.login(member);
		
		return Ut.jsReplace("로그인 성공", "/");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {

		if (!rq.isLogined()) {
			return Ut.jsHistoryBack("로그아웃 상태입니다");
		}

		rq.logout();
		
		return Ut.jsReplace("로그아웃 성공", "/");
	}
	
	@RequestMapping("/usr/member/profile")
	public String showProfile(Model model) {
		Member member = rq.getLoginedMember(); 
		model.addAttribute("member", member);
		return "usr/member/profile";
	}
}