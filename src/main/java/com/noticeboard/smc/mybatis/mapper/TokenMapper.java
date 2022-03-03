package com.noticeboard.smc.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.noticeboard.smc.model.dto.TokenDTO;

@Mapper
public interface TokenMapper {
	
	public TokenDTO getEnableTokenByUserId(@Param("userId") String userId);
	
	public int insertToken(@Param("userId") String userId,@Param("refreshToken") String refreshToken);
	
	public void setTokenUnable(@Param("userId") String userId);
}
