package com.kkwo.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kkwo.demo.vo.Rq;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
	@Autowired
	private Rq rq;

	// 로그인이 필요한 페이지에 접근하기 전 실행되는 인터셉터 클래스

	/**
	 * 로그인 여부를 체크하여 처리를 결정하는 메서드
	 * 
	 * @param req     HttpServletRequest
	 * @param resp    HttpServletResponse
	 * @param handler Object 타입
	 * 
	 * @return 성공 : 다음 단계 실행, 실패 : 실행 중단 ( 페이지 이동 )
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

		if (!rq.isLogined()) {
			rq.printJsReplace("로그인 후 이용해주세요", "/usr/member/login");
			return false;
		}

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
