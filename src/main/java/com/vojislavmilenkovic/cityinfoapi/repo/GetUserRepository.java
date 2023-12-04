package com.vojislavmilenkovic.cityinfoapi.repo;

import com.vojislavmilenkovic.cityinfoapi.dto.UserDTO;

@FunctionalInterface
public interface GetUserRepository {
	UserDTO getUser(String username);
}
