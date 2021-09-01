package com.senchaMsa.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senchaMsa.domain.Member;
import com.senchaMsa.dto.LoginInfo;
import com.senchaMsa.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	
	private final MemberService memberService;
	
	@PostMapping("/loginInfo")
	public Map<String, Object> loginInfo(HttpServletRequest request, LoginInfo loginMember) {
		
		Map<String, Object> result = new HashMap<>();
		
		Member findMember = memberService.findJoinedMember(loginMember.getName(), loginMember.getPassword());
		
		if(findMember != null) {
			
			@SuppressWarnings("unchecked")
			Map<String, Object> session  = (Map<String, Object>) request.getSession().getAttribute("loginInfo");
			
			// 3. 처음엔 null이지만 다시 조회할땐 값이 들어 있다.
			System.out.println(session);
			
			result.put("member", findMember);
			result.put("code", 200);
			
			// 2. 여기까지 왔다면 로그인 계정 정보가 존재한다는 의미고 session에 해당 정보를 등록
			
			request.getSession().setAttribute("loginInfo", result);
			
			
		} else {
			result.put("msg", "해당 계정이 존재하지 않습니다.");
			result.put("code", 404);
		}
		
		return result;
	}
	
	@GetMapping("/fail")
	public Map<String, Object> fail() {
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 999);  // 1. 999라는 코드가 전달되면 화면 단에선 로그인페이지로 redirect
		
		return result;
	}
}
