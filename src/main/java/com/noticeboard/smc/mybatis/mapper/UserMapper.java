package com.noticeboard.smc.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.noticeboard.smc.model.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	public int insertUser(UserDTO userDTO);
	
	public UserDTO getUserById(@Param("userId") String userId);
	
}
