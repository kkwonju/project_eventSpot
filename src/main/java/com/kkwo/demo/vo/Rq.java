package com.kkwo.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.kkwo.demo.service.MemberService;
import com.kkwo.demo.util.Ut;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;

	// 사용자 요청과 로그인 정보를 담고있는 Rq 클래스

	// HttpServletRequest, HttpServletResponse, MemberService 객체를 주입받는 생성자
	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();

		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		// session 속성을 불러와 판단
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
	}

	/**
	 * 로그인 메서드
	 * 
	 * @param member Member 객체
	 *
	 * @return void형
	 */
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	/**
	 * 로그아웃 메서드
	 * 
	 * @return void형
	 */
	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

	/**
	 * view 연결, (javascript) alert창 메세지 보여준 후 이전 페이지 이동
	 * 
	 * @param resultMsg 보여줄 메세지
	 *
	 * @return js.jsp view 반환
	 */
	public String jsHistoryBackOnView(String resultMsg) {
		req.setAttribute("resultMsg", resultMsg);
		req.setAttribute("historyBack", true);
		return "usr/common/js";
	}

	/**
	 * view 연결, (javascript) alert창 메세지 보여준 후 replaceUri 이동
	 * 
	 * @param resultMsg 보여줄 메세지
	 *
	 * @return js.jsp view 반환
	 */
	public String jsReplaceOnView(String resultMsg, String replaceUri) {
		req.setAttribute("resultMsg", resultMsg);
		req.setAttribute("replaceUri", replaceUri);
		return "usr/common/js";
	}

	/**
	 * (javascript) alert창 메세지 보여준 후 이전 페이지 이동
	 * 
	 * @param resultMsg  보여줄 메세지
	 * @param replaceUri 이동할 uri
	 */
	public void printJsReplace(String resultMsg, String replaceUri) {
		resp.setContentType("text/html; charset=UTF-8");
		print(Ut.jsReplace(resultMsg, replaceUri));
	}

	/**
	 * @param str 화면에 보여줄 문자열
	 */
	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
