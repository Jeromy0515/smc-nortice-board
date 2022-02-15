package com.norticeboard.smc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.norticeboard.smc.model.dto.UserDTO;
import com.norticeboard.smc.mybatis.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public int insertUser(UserDTO userDTO) {
		return userMapper.insertUser(userDTO);
	}
	
	public UserDTO getUserById(String userId) {
		return userMapper.getUserById(userId);
	}
	
}
