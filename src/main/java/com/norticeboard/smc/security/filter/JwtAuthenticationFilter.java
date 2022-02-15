package com.norticeboard.smc.security.filter;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.norticeboard.smc.model.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if(!request.getMethod().equals("POST"))
			throw new AuthenticationServiceException("Authentication method not supported: "+ request.getMethod());
		
		String userName = request.getParameter("id");
		String userPassword = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, userPassword);
		
		return this.getAuthenticationManager().authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		UserDTO user = (UserDTO) authentication.getPrincipal();
		Key signedKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(secretKey), SignatureAlgorithm.HS256.getJcaName());
		String accessToken = Jwts.builder()
				.setSubject(user.getUserName())
				.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.setIssuer(request.getRequestURL().toString())
				.signWith(SignatureAlgorithm.HS256, signedKey)
				.compact();
		String refreshToken = Jwts.builder()
				.setSubject(user.getUserName())
				.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.setIssuer(request.getRequestURL().toString())
				.signWith(SignatureAlgorithm.HS256, signedKey)
				.compact();
		response.setHeader("access_token", accessToken);
		response.setHeader("refresh_token", refreshToken);
  	}
	
	
}
