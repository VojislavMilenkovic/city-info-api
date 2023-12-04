package com.vojislavmilenkovic.cityinfoapi.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vojislavmilenkovic.cityinfoapi.dto.UserDTO;
import com.vojislavmilenkovic.cityinfoapi.repo.GetUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private GetUserRepository getUserRepository;
	
	public CustomUserDetailsService(GetUserRepository getUserRepository) {
		super();
		this.getUserRepository = getUserRepository;
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = getUserRepository.getUser(username);
		return new User(user.getUsername(), user.getPassword(), grantedAuthorityRoles("USER"));
	}
	
	private Collection<GrantedAuthority> grantedAuthorityRoles(String role){
		return Collections.singleton(new SimpleGrantedAuthority(role)) ;
	}

}
