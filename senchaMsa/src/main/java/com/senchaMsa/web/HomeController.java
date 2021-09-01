package com.senchaMsa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}

/*
 *  - 프로젝트 안에 sencha를 건들 때
 *  (1) cmd로 프로젝트 webapp 까지 들어간 뒤
 *  (2) sencha app watch로 실행 시킨 뒤 작업 진행.
 * 
 */
 