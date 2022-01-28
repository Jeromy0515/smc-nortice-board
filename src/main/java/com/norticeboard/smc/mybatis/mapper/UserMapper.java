package com.norticeboard.smc.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class UserMapper {

	final String getUserByIdAndPassword = "SELECT * FROM user WHERE user_id LIKE #{id} AND user_pw LIKE #{password}";
	final String insertUser = "INSERT INTO user VALUES(#{id},#{password},#{name})";
	final String deleteUser = "DELETE FROM user WHERE user_id LIKE #{id}";
	
}
