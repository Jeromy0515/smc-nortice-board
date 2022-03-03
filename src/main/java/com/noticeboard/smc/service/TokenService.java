package com.noticeboard.smc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noticeboard.smc.model.dto.TokenDTO;
import com.noticeboard.smc.mybatis.mapper.TokenMapper;

@Service
public class TokenService {
	
	@Autowired
	private TokenMapper tokenMapper;
	
	public TokenDTO getEnableTokenByUserId(String userId) {
		return tokenMapper.getEnableTokenByUserId(userId);
	}
	
	public int insertToken(String userId, String refreshToken) {
		return tokenMapper.insertToken(userId, refreshToken);
	}
	
	public void setTokenUnable(String userId) {
		tokenMapper.setTokenUnable(userId);
	}
	
}
