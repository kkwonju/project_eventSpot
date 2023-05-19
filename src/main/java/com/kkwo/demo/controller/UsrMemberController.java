package com.kkwo.demo.controller;

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

	// 사용자 회원 컨트롤러 클래스
	
	// MemberService 객체를 주입받는 생성자
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/**
	 * 회원가입 양식을 보여주는 메서드;
	 * 사용자용 회원가입 페이지를 반환한다
	 * */
	@RequestMapping("/usr/member/join")
	public String showJoinForm() {
		return "usr/member/join";
	}

	/**
	 * 회원가입 메서드;
	 * 입력값 유효성 검사를 진행하고 회원가입을 시도한다
	 * 시도 결과에 따라 다른 javascript 코드를 반환한다
	 * 
	 * @param 로그인 아이디, 로그인 비밀번호, 닉네임, 이메일
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 * */
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String nickname, String email) {

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

	/**
	 * 로그인 양식을 보여주는 메서드;
	 * 사용자용 로그인 페이지를 반환한다
	 * */
	@RequestMapping("/usr/member/login")
	public String showLoginForm() {
		return "usr/member/login";
	}
	
	/**
	 * 로그인 메서드;
	 * 입력값 유효성 검사를 진행한다
	 * 입력된 아이디와 비밀번호가 일치하는지 검사한다
	 * Rq 클래스의 login 메서드를 활용해 member 객체를 session에 추가한다
	 * 시도 결과에 따라 다른 javascript 코드를 반환한다
	 * 
	 * @param 로그인 아이디, 로그인 패스워드
	 * @return 성공 : history.back(); 실패 : location.replace(replaceUri);
	 * */
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

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

	/**
	 * 로그아웃 메서드;
	 * Rq 클래스에 저장된 로그인 상태를 체크한 후 로그아웃을 시도한다
	 * 시도 결과에 따라 다른 javascript 코드를 반환한다
	 * 
	 * @return 성공 : history.back(); 실패 : location.replace(replaceUri);
	 * */
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {

		if (!rq.isLogined()) {
			return Ut.jsHistoryBack("로그아웃 상태입니다");
		}

		rq.logout();
		
		return Ut.jsReplace("로그아웃 성공", "/");
	}
	
	/**
	 * 회원정보를 보여주는 메서드;
	 * Rq 클래스에 저장된 회원 데이터를 불러와 모델에 추가하고, 사용자용 회원정보 페이지를 반환한다
	 * */
	@RequestMapping("/usr/member/profile")
	public String showProfile(Model model) {
		Member member = rq.getLoginedMember(); 
		model.addAttribute("member", member);
		return "usr/member/profile";
	}
}