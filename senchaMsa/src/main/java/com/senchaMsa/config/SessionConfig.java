package com.senchaMsa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		// 1. /api/**로 오는 모든 경로에 interceptor를 걸어준다.
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**");
	}
}
