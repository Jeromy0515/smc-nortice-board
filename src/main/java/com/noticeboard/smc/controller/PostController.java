package com.noticeboard.smc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.noticeboard.smc.model.dto.UserDTO;
import com.noticeboard.smc.security.service.SecurityService;
import com.noticeboard.smc.service.TokenService;
import com.noticeboard.smc.service.UserService;

@RestController
public class PostController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/auth/login")
	public void login(HttpServletResponse response, HttpServletRequest request, @RequestParam Map<String, Object> param) throws IOException{
		
		Map<String, Object> responseData = new HashMap<String, Object>();

		UserDTO user = userService.getUserById(String.valueOf(param.get("id")));
		
		if(user != null && passwordEncoder.matches(String.valueOf(param.get("password")), user.getUserPassword())) {
			
			responseData.put("isLogin", "true");
			
			Cookie[] requestCookies = request.getCookies();
			
			if(requestCookies != null) {
				
				boolean hasRefreshToken = false;
				
				for(Cookie reqestCookie : request.getCookies()) {
					
					if(reqestCookie.getName().equals("SNB_RT")) {
						hasRefreshToken = true;
						break;
					}
						
				}
				
				if(!hasRefreshToken) {
					tokenService.setTokenUnable(user.getUserId());
				}
				
			} else {
				
				tokenService.setTokenUnable(user.getUserId());
				
			}
			
			if(tokenService.getEnableTokenByUserId(user.getUserId()) == null) {
				
				String refreshToken = securityService.createRefreshToken(user.getUserName(), request.getRequestURL().toString());
				
				Cookie refreshTokenCookie = createCookie("SNB_RT", refreshToken, 60 * 60 * 24 * 14);
				
				response.addCookie(refreshTokenCookie);
				
				tokenService.insertToken(user.getUserId(), refreshToken);
				
			}
			
			String accessToken = securityService.createAccessToken(user.getUserName(), request.getRequestURL().toString());
			
			Cookie accessTokenCookie = createCookie("SNB_AT", accessToken, 10 * 60);
			
			response.addCookie(accessTokenCookie);
			
		} else {
			
			responseData.put("isLogin", "false");
			
		}
			
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(responseData));
	}	
	
	@PostMapping("/create_account")
	public void signup(@RequestParam Map<String, Object> param) {
		
		UserDTO userDTO = new UserDTO(param);
		userDTO.setUserPassword(passwordEncoder.encode(String.valueOf(param.get("password"))));
		userService.insertUser(userDTO);
		
	}
	
	@PostMapping("/create_account/overlap")
	public void overlap(HttpServletResponse response,@RequestParam("id") String userId) throws IOException{
		
		Map<String, Object> responseData = new HashMap<String, Object>();
		
		if(userService.getUserById(userId) == null) 
			responseData.put("isOverlap", "false");
		else 
			responseData.put("isOverlap", "true");
			
		response.getWriter().print(new Gson().toJson(responseData));
		
	}
	
	private Cookie createCookie(String name, String value, int age) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(age);
		cookie.setPath("/");
//		cookie.setHttpOnly(true);
//		cookie.setSecure(true);
		
		return cookie;
	}
}
