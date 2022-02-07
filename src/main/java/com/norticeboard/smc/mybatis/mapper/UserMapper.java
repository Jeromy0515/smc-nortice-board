package com.norticeboard.smc.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.norticeboard.smc.model.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	public int insertUser(UserDTO userDTO);
	
	public UserDTO getUserById(@Param("userId") String userId);
	
	public UserDTO login(@Param("userId") String userId,@Param("userPassword") String userPassword);
	
	
}
