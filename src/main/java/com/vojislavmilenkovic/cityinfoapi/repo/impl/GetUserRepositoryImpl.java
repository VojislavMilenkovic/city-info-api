package com.vojislavmilenkovic.cityinfoapi.repo.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.vojislavmilenkovic.cityinfoapi.dto.UserDTO;
import com.vojislavmilenkovic.cityinfoapi.dto.mapper.UserDTORowMapper;
import com.vojislavmilenkovic.cityinfoapi.exceptions.CityException;
import com.vojislavmilenkovic.cityinfoapi.repo.GetUserRepository;
import com.vojislavmilenkovic.cityinfoapi.utils.CityUtil;

@Repository
public class GetUserRepositoryImpl implements GetUserRepository {

	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	private static final String GET_USER_QUERY = CityUtil.slurp("sql/get_user_query.sql");

	public GetUserRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		super();
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	@Override
	public UserDTO getUser(String username) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("username", username.trim());
		try {
			UserDTO user = namedJdbcTemplate.queryForObject(GET_USER_QUERY, paramMap, new UserDTORowMapper());

			if (user != null) {
				return user;
			} else {
				throw new UsernameNotFoundException("Wrong username or password");
			}

		} catch (DataAccessException e) {
			throw new CityException("Error while getting user: " + e.getMessage());

		}
	}

}
