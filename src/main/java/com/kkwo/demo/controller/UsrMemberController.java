package com.kkwo.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.MemberService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Member;
import com.kkwo.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private HttpSession httpSession;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String nickname, String email) {
		boolean isLogined = httpSession.getAttribute("loginedMemberId") != null;

		if (isLogined) {
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

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw) {

		boolean isLogined = httpSession.getAttribute("loginedMemberId") != null;

		if (isLogined) {
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

		httpSession.setAttribute("loginedMemberId", member.getId());

		return ResultData.buildResultData("S-1", "로그인 성공");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout() {
		boolean isLogined = httpSession.getAttribute("loginedMemberId") != null;

		if (!isLogined) {
			return ResultData.buildResultData("F-A", "로그아웃 상태입니다");
		}

		httpSession.removeAttribute("loginedMemberId");
		
		return ResultData.buildResultData("S-1", "로그아웃 성공");
	}
}