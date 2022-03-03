package com.noticeboard.smc.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noticeboard.smc.security.service.SecurityService;

@RestController
public class SecurityController {

	@Autowired
	SecurityService securityService;
	
	@GetMapping("/create/token")
	public Map<String, Object> createToken(@RequestParam(value = "subject") String userId) {
		String token = securityService.createToken(userId);
		Map<String, Object> accessToken = new LinkedHashMap<>();
		accessToken.put("access_token", token);
		return accessToken;
	}
	
	@GetMapping("/get/subject")
	public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
		String subject = securityService.getSubject(token);
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("subject", subject);
		return result;
	}
	
}
