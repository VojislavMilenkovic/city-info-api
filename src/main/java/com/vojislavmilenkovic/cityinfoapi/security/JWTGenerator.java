package com.vojislavmilenkovic.cityinfoapi.security;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {
	
	private static final long JWT_EXPIRE_TIME = 8000;
	private static String JWT_SECRET;
	
	
	static {
        // Generate a 32-byte random key
        byte[] keyBytes = new byte[32];
        new SecureRandom().nextBytes(keyBytes);

        // Encode the key as a Base64 string
        JWT_SECRET = Base64.getEncoder().encodeToString(keyBytes);
    }
	

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRE_TIME);
		byte[] secret = JWT_SECRET.getBytes();
		String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
		return token;
	}
	
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	

	
	public boolean validateToken(String token) {
		try {
			 Claims claims = Jwts.parserBuilder()
	                    .setSigningKey(JWT_SECRET)
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody();
			return true;
		}catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT expired or incorrect");
		}
	}
}
