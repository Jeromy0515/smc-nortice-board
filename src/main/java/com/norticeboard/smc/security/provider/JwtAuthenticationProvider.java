package com.norticeboard.smc.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userName =  authentication.getPrincipal().toString();
		String userPassword = authentication.getCredentials().toString();
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, userPassword);
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
	
	public boolean isCorrectPassword(String password) {
		return false;
	}
	
}
