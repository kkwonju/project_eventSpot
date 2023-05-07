package com.kkwo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.MemberService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String nickname, String email) {
		if(Ut.isEmpty(loginId)) {
			return ResultData.buildResultData("F-IN", "로그인 아이디를 입력하세요");
		}
		if(Ut.isEmpty(loginPw)) {
			return ResultData.buildResultData("F-IN", "로그인 패스워드를 입력하세요");
		}
		if(Ut.isEmpty(nickname)) {
			return ResultData.buildResultData("F-IN", "닉네임을 입력하세요");
		}
		if(Ut.isEmpty(email)) {
			return ResultData.buildResultData("F-IN", "이메일를 입력하세요");
		}
		
		ResultData joinMemberRd = memberService.doJoinMember(loginId, loginPw, nickname, email);
		
		if(joinMemberRd.isFail()) {
			return ResultData.buildResultData(joinMemberRd.getResultCode(), joinMemberRd.getResultMsg());
		}
		
		return ResultData.buildResultData("S-1", Ut.f("%s님 회원가입 성공", nickname));
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw) {
		if(Ut.isEmpty(loginId)) {
			return ResultData.buildResultData("F-IN", "로그인 아이디를 입력하세요");
		}
		if(Ut.isEmpty(loginPw)) {
			return ResultData.buildResultData("F-IN", "로그인 패스워드를 입력하세요");
		}
		
		ResultData loginMemberRd = memberService.doLoginMember(loginId, loginPw);
		
		if(loginMemberRd.isFail()) {
			return ResultData.buildResultData(loginMemberRd.getResultCode(), loginMemberRd.getResultMsg());
		}
		
		return ResultData.buildResultData("S-1", "로그인 성공");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout() {
		return ResultData.buildResultData("S-1", "로그아웃 성공");
	}
}