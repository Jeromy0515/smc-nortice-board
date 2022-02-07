package com.norticeboard.smc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.norticeboard.smc.model.dto.UserDTO;
import com.norticeboard.smc.mybatis.mapper.UserMapper;

@Controller
public class PostController {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public void login(HttpServletResponse response, @RequestParam Map<String, Object> param) throws IOException{
		
		Map<String, Object> responseData = new HashMap<String, Object>();

		UserDTO user = userMapper.getUserById(String.valueOf(param.get("id")));
		
		System.out.println(user.getUserPassword());
		
		if(user != null && passwordEncoder.matches(String.valueOf(param.get("password")), user.getUserPassword()))
			responseData.put("isLogin", "true");
		else
			responseData.put("isLogin", "false");
		
		response.getWriter().print(new Gson().toJson(responseData));
	}	
	
	@PostMapping("/signup")
	public void signup(@RequestParam Map<String, Object> param) {
		UserDTO userDTO = new UserDTO(param);
		userDTO.setUserPassword(passwordEncoder.encode(String.valueOf(param.get("password"))));
		userMapper.insertUser(userDTO);
	}
	
	@PostMapping("/signup.overlap")
	public void overlap(HttpServletResponse response,@RequestParam("id") String userId) throws IOException{
		
		Map<String, Object> responseData = new HashMap<String, Object>();
		
		if(userMapper.getUserById(userId) == null) 
			responseData.put("isOverlap", "false");
		else 
			responseData.put("isOverlap", "true");
			
		response.getWriter().print(new Gson().toJson(responseData));
		
	}
}
