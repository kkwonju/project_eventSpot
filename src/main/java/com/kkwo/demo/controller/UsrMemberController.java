package com.kkwo.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/join")
	public String showJoinForm() {
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(HttpServletRequest req, String loginId, String loginPw, String nickname, String email) {
		Rq rq = new Rq(req, memberService);

		if (rq.isLogined()) {
			return ResultData.buildResultData("F-A", "이미 로그인 중입니다");
		}

		if (Ut.isEmpty(loginId)) {
			return ResultData.buildResultData("F-IN", "로그인 아이디를 입력하세요");
		}
		if (Ut.isEmpty(loginPw)) {
			return ResultData.buildResultData("F-IN", "로그인 패스워드를 입력하세요");
		}
		if (Ut.isEmpty(nickname)) {
			return ResultData.buildResultData("F-IN", "닉네임을 입력하세요");
		}
		if (Ut.isEmpty(email)) {
			return ResultData.buildResultData("F-IN", "이메일를 입력하세요");
		}

		ResultData joinMemberRd = memberService.doJoinMember(loginId, loginPw, nickname, email);

		if (joinMemberRd.isFail()) {
			return (ResultData) joinMemberRd;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		return ResultData.buildResultData("S-1", Ut.f("%s님 회원가입 성공", member.getNickname()));
	}

	@RequestMapping("/usr/member/login")
	public String showLoginForm() {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpServletRequest req, String loginId, String loginPw) {
		Rq rq = new Rq(req, memberService);

		if (rq.isLogined()) {
			return ResultData.buildResultData("F-A", "이미 로그인 중입니다");
		}

		if (Ut.isEmpty(loginId)) {
			return ResultData.buildResultData("F-IN", "로그인 아이디를 입력하세요");
		}
		if (Ut.isEmpty(loginPw)) {
			return ResultData.buildResultData("F-IN", "로그인 패스워드를 입력하세요");
		}

		ResultData loginMemberRd = memberService.doLoginMember(loginId, loginPw);

		if (loginMemberRd.isFail()) {
			return (ResultData) loginMemberRd;
		}

		Member member = memberService.getMemberByLoginId(loginId);
		
		rq.login(member);
		
		return ResultData.buildResultData("S-1", "로그인 성공");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpServletRequest req) {
		Rq rq = new Rq(req, memberService);

		if (!rq.isLogined()) {
			return ResultData.buildResultData("F-A", "로그아웃 상태입니다");
		}

		rq.logout();
		
		return ResultData.buildResultData("S-1", "로그아웃 성공");
	}
}