package com.vojislavmilenkovic.cityinfoapi.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vojislavmilenkovic.cityinfoapi.dto.UserDTO;

public class UserDTORowMapper implements RowMapper<UserDTO> {

	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserDTO dto = new UserDTO();
		dto.setUsername(rs.getString("username"));
		dto.setPassword(rs.getString("password"));

		return dto;
	}

}
