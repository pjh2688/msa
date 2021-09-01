package com.senchaMsa.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. 세션정보에 로그인 정보(loginInfo)가 없다면 
		if(request.getSession().getAttribute("loginInfo") == null) {
			
			response.sendRedirect("/fail");
			
			return false;  // 2. return false; -> 그냥 넘어간다.
		}
		
		return true;  // 3. 그냥 안넘어 간다.
	}
	

}
