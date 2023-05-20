package com.kkwo.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kkwo.demo.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	private Rq rq;

	// 사용자의 요청이 처리되기 전 실행되는 인터셉터 클래스

	// Rq 객체를 주입받는 생성자
	public BeforeActionInterceptor(Rq rq) {
		this.rq = rq;
	}

	/**
	 * rq 객체를 HttpServletRequest 의 속성으로 추가하는 메서드
	 * 
	 * @param req     HttpServletRequest
	 * @param resp    HttpServletResponse
	 * @param handler Object 타입
	 * 
	 * @return 성공 : 다음 단계 실행
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

		req.setAttribute("rq", rq);

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
