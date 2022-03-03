package com.noticeboard.smc.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noticeboard.smc.model.dto.HealthChecker;
import com.noticeboard.smc.model.dto.UserDTO;


@Controller
public class ViewController {
	
	@GetMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		
		model.addAttribute("anchorText", hasAccessToken(request) ? "로그아웃" : "로그인");
		
		return "home";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/create_account")
	public String signup(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("user", userDTO);
		return "signup";
		
	}
	
	@ResponseBody
	@RequestMapping("/health") 
	public HealthChecker health() {
		HealthChecker healthChecker = new HealthChecker();
		healthChecker.setStatus("ok");
		return healthChecker;
	}
	
	
	
	private boolean hasAccessToken(HttpServletRequest request) {
		Cookie[] requestCookies = request.getCookies();
		
		if(requestCookies != null) {
			
			for(Cookie cookie : requestCookies) {
				if(cookie.getName().equals("SNB_AT")) 
					return true;
			}
			
		}
		return false;
	}
		
	
}
