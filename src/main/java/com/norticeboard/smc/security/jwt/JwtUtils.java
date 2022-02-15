package com.norticeboard.smc.security.jwt;


import java.security.Key;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtUtils {
	
	@Value("${jwt.secret}")
	private String secretKey;

	private Key signedKey;
	
	@PostConstruct
	private void init() {
		this.signedKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(secretKey), SignatureAlgorithm.HS256.getJcaName());
	}
	
	public String createToken(String userName) {
		 
		Claims claims = Jwts.claims().setSubject(userName);
		
		Date currentTime = new Date();
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(currentTime)
				.signWith(SignatureAlgorithm.HS256, signedKey)
				.setExpiration(new Date(currentTime.getTime() + 30 * 60 * 1000))
				.compact();
	}
	
	public String createAccessToken(String userName, String iss) {
		
		Claims claims = Jwts.claims().setSubject(userName);
		
		Date currentTime = new Date();
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(currentTime)
				.setExpiration(new Date(currentTime.getTime() + 30 * 60 * 1000))
				.setIssuer(iss)
				.signWith(SignatureAlgorithm.HS256, signedKey)
				.compact();
		
	}
	
	public String createRefreshToken(String userName, String iss) {
		
		Claims claims = Jwts.claims().setSubject(userName);
		
		Date currentTime = new Date();
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(currentTime)
				.setExpiration(new Date(currentTime.getTime() + 20160 * 60 * 1000))
				.setIssuer(iss)
				.signWith(SignatureAlgorithm.HS256, signedKey)
				.compact();
	}
	
	public String getSubject(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
	
}
