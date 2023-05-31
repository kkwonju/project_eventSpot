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

import ch.qos.logback.core.joran.conditional.IfAction;

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
	 * @return 사용자용 회원가입 페이지 반환
	 */
	@RequestMapping("/usr/member/join")
	public String showJoinForm() {
		return "usr/member/join";
	}

	/**
	 * 회원가입 핸들러 메서드
	 * 
	 * TB_MEMBER / 등록, 조회
	 * 
	 * @param loginId  로그인 아이디
	 * @param loginPw  로그인 비밀번호
	 * @param nickname 닉네임
	 * @param email    이메일
	 * 
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String nickname, String email) {

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
	 * @return 사용자용 로그인 페이지 반환
	 */
	@RequestMapping("/usr/member/login")
	public String showLoginForm() {
		return "usr/member/login";
	}

	/**
	 * 로그인 핸들러 메서드
	 * 
	 * TB_MEMBER / 조회
	 * 
	 * @param 로그인 아이디, 로그인 패스워드
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		if (Ut.isEmpty(loginId)) {
			return Ut.jsHistoryBack("로그인 아이디를 입력하세요");
		}
		if (Ut.isEmpty(loginPw)) {
			return Ut.jsHistoryBack("로그인 패스워드를 입력하세요");
		}

		// 아이디, 비밀번호 일치 여부
		ResultData loginRd = memberService.doLoginMember(loginId, loginPw);

		if (loginRd.isFail()) {
			return Ut.jsHistoryBack(loginRd.getResultMsg());
		}

		Member member = memberService.getMemberByLoginId(loginId);

		// session에 member.id 추가
		rq.login(member);

		return Ut.jsReplace("로그인 성공", "/");
	}

	/**
	 * 로그아웃 핸들러 메서드
	 * 
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {

		// session에 member.id 삭제
		rq.logout();

		return Ut.jsReplace("로그아웃 성공", "/");
	}

	/**
	 * @return 사용자용 회원정보 페이지 반환
	 */
	@RequestMapping("/usr/member/profile")
	public String showProfile(Model model) {
		Member member = rq.getLoginedMember();
		model.addAttribute("member", member);
		return "usr/member/profile";
	}

	/**
	 * @return 사용자용 회원정보 수정 페이지 반환
	 */
	@RequestMapping("/usr/member/modify")
	public String showModifyPage() {
		return "usr/member/modify";
	}

	/**
	 * @param nickname 새 닉네임
	 * @param loginPw 새 비밀번호
	 * 
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String nickname, String loginPw) {

		if (Ut.isEmpty(nickname)) {
			return Ut.jsHistoryBack("닉네임을 입력해주세요");
		}
		if (Ut.isEmpty(loginPw)) {
			loginPw = null;
		} else {
			loginPw = Ut.sha256(loginPw);
		}

		ResultData memberModifyRd = memberService.doModify(rq.getLoginedMemberId(), nickname, loginPw);

		if (memberModifyRd.isFail()) {
			return Ut.jsHistoryBack(memberModifyRd.getResultMsg());
		}

		Member member = memberService.getMemberById(rq.getLoginedMemberId());

		return Ut.jsReplace("회원정보가 수정되었습니다", "../member/profile");
	}
	
	// 아이디 찾기 페이지
	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginIdPage() {
		return "usr/member/findLoginId";
	}
	
	// 아이디 찾기
	@RequestMapping("/usr/member/doFindLoginId")
	@ResponseBody
	public String doFindLoginId(String email) {
		if (Ut.isEmpty(email)) {
			return Ut.jsHistoryBack("이메일을 입력해주세요");
		}
		
		Member member = memberService.getMemberByEmail(email);

		if (member == null) {
			return Ut.jsHistoryBack(Ut.f("일치하는 이메일이 없습니다"));
		}
		
		return Ut.jsReplace(Ut.f("LoginId : %s", member.getLoginId()), "/usr/member/login");
	}
	
	// 비밀번호 찾기 페이지
	@RequestMapping("/usr/member/findLoginPw")
	public String showFindLoginPwForm() {
		return "usr/member/findLoginPw";
	}
	
	// 비밀번호 찾기
	@RequestMapping("/usr/member/doFindLoginPw")
	@ResponseBody
	public String doFindLoginPw(String loginId, String email) {
		if (Ut.isEmpty(loginId)) {
			return Ut.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Ut.isEmpty(email)) {
			return Ut.jsHistoryBack("이메일을 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Ut.jsHistoryBack(Ut.f("일치하는 아이디가 없습니다"));
		}

		if (!member.getEmail().equals(email)) {
			return Ut.jsHistoryBack(Ut.f("일치하는 이메일이 없습니다"));
		}
		
		ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempLoginPwByEmail(member);
		
		return Ut.jsReplace(notifyTempLoginPwByEmailRd.getResultMsg(), "/usr/member/login");
	}
}