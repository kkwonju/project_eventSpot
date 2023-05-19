package com.kkwo.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kkwo.demo.vo.Rq;

@Component
public class NeedLogoutInterceptor implements HandlerInterceptor {
	@Autowired
	private Rq rq;

	// 로그아웃이 필요한 페이지에 접근하기 전 실행되는 인터셉터 클래스
	
	
	// 로그아웃 여부를 체크하여 처리를 결정하는 메서드
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

		if (rq.isLogined()) {
			rq.printJsReplace("로그아웃 후 이용해주세요", "/");
			return false;
		}

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
