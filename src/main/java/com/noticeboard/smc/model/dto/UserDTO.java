package com.noticeboard.smc.model.dto;

import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDTO {
	
	private String userName;
	private String userId;
	private String userPassword;
		
	public UserDTO() {
	}
	
	public UserDTO(Map<String, Object> param) {
		set(param);
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void set(Map<String, Object> param) {
		this.userName = String.valueOf(param.get("name"));
		this.userId = String.valueOf(param.get("id"));
		this.userPassword = String.valueOf(param.get("password"));
	}
	
	
}
