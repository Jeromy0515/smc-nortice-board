package com.norticeboard.smc.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthorizationToken extends UsernamePasswordAuthenticationToken{

	public JwtAuthorizationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
}
