package com.senchaMsa.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginApiController {

	@PostMapping("/sessionCheck")
	public Map<String, Object> sessionCheck(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 200);
		return result;
	}
}
