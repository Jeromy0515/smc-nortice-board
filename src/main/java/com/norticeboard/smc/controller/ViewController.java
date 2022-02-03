package com.norticeboard.smc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.norticeboard.smc.model.dto.HealthChecker;
import com.norticeboard.smc.model.dto.UserDTO;


@Controller
public class ViewController {
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
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
		
	
}
