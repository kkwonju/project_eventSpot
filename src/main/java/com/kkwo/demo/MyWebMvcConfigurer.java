package com.kkwo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kkwo.demo.interceptor.BeforeActionInterceptor;
import com.kkwo.demo.interceptor.NeedLoginInterceptor;
import com.kkwo.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	@Autowired
	NeedLogoutInterceptor needLogoutInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		InterceptorRegistration ir;

		ir = registry.addInterceptor(beforeActionInterceptor);
		ir.addPathPatterns("/**");
		ir.addPathPatterns("/favicon.ico");
		ir.excludePathPatterns("/resource/**");
		ir.excludePathPatterns("/error");
		
		ir = registry.addInterceptor(needLoginInterceptor);
		ir.addPathPatterns("/usr/member/profile");
		ir.addPathPatterns("/usr/member/modify");
		ir.addPathPatterns("/usr/member/doLogout");
		
		ir = registry.addInterceptor(needLogoutInterceptor);
		ir.addPathPatterns("/usr/member/join");
		ir.addPathPatterns("/usr/member/doJoin");
		ir.addPathPatterns("/usr/member/login");
		ir.addPathPatterns("/usr/member/doLogin");
		
	}
}