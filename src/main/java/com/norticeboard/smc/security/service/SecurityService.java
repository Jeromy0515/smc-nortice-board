package com.norticeboard.smc.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.norticeboard.smc.security.jwt.JwtUtils;

@Service
public class SecurityService {
	
	@Autowired
	JwtUtils jwtUtils;
	
	public String createToken(String userName) {
		return jwtUtils.createToken(userName);
	}
	
	public String createAccessToken(String userName, String iss) {
		return jwtUtils.createAccessToken(userName, iss);
	}
	
	public String createRefreshToken(String userName, String iss) {
		return jwtUtils.createRefreshToken(userName, iss);
	}
	
	public String getSubject(String token) {
		return jwtUtils.getSubject(token);
	}
	
}
