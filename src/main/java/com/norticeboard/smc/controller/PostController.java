package com.norticeboard.smc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.norticeboard.smc.model.dto.UserDTO;
import com.norticeboard.smc.mybatis.mapper.UserMapper;
import com.norticeboard.smc.service.UserService;

@Controller
public class PostController {
	
	@Autowired
	UserMapper userMapper;
	
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute("user") UserDTO userDTO) {
		System.out.println("userName : " + userDTO.getUserName());
		System.out.println("userId : " + userDTO.getUserId());
		System.out.println("userPassword : " + userDTO.getUserPassword());
		userMapper.insertUser(userDTO);
		return "redirect:/login";
	}
	
}
