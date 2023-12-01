package com.vojislavmilenkovic.cityinfoapi.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;

public class CityDTORowMapper implements RowMapper<CityDTO> {

	@Override
	public CityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CityDTO dto = new CityDTO();
		dto.setId(rs.getInt("id"));
		dto.setName(rs.getString("name"));
		dto.setCountry(rs.getString("country"));
		dto.setState(rs.getString("state"));
		dto.setRegion(rs.getString("region"));
		dto.setPopulation(rs.getString("population"));
		return dto;
	}

}
