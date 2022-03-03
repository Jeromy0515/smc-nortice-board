package com.noticeboard.smc.model.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenDTO {
	
	private String userId;
	private String refreshToken;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
