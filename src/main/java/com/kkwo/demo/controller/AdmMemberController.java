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

	// 관리자 회원 컨트롤러 클래스

	// MemberService 객체를 주입받는 생성자
	public AdmMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * TB_MEMBER / 조회
	 * 
	 * @return 관리자용 이벤트 리스트 페이지 반환
	 */
	@RequestMapping("/admin/manage/memberList")
	public String showMemberList(Model model) {
		List<Member> members = memberService.getMembers();
		model.addAttribute("members", members);
		return "admin/manage/memberList";
	}

	/**
	 * 회원 삭제 핸들러 메서드
	 * 
	 * TB_MEMBER / 삭제
	 * 
	 * @param id 삭제할 회원 ID
	 * 
	 * @return 성공 : location.replace(replaceUri); 실패 : history.back();
	 */
	@RequestMapping("/admin/manage/deleteMember")
	@ResponseBody
	public String deleteMember(int id) {
		int result = memberService.deleteMember(id);

		// 회원 삭제를 시도하고 삭제 결과에 따라 처리 ( 실패 : -1 )
		if (result == -1) {
			return Ut.jsHistoryBack("회원 탈퇴 실패");
		}
		return Ut.jsReplace(Ut.f("%d번 회원 탈퇴 성공", id), "/admin/manage/memberList");
	}
}