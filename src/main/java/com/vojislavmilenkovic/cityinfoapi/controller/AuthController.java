package com.vojislavmilenkovic.cityinfoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vojislavmilenkovic.cityinfoapi.dto.AuthResponseDTO;
import com.vojislavmilenkovic.cityinfoapi.dto.LoginDTO;
import com.vojislavmilenkovic.cityinfoapi.security.JWTGenerator;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private JWTGenerator jwtGenerator;

	public AuthController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping("login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerator.generateToken(authentication);
		return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(token));
	}

}
