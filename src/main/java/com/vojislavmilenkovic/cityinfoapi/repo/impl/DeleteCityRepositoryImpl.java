package com.vojislavmilenkovic.cityinfoapi.repo.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vojislavmilenkovic.cityinfoapi.exceptions.CityException;
import com.vojislavmilenkovic.cityinfoapi.repo.DeleteCityRepository;
import com.vojislavmilenkovic.cityinfoapi.utils.CityUtil;

@Repository
public class DeleteCityRepositoryImpl implements DeleteCityRepository {

	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	private static final String DELETE_CITY_QUERY = CityUtil.slurp("sql/delete_city_query.sql");

	public DeleteCityRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		super();
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	@Override
	public void deleteCity(int id) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		try {
			namedJdbcTemplate.update(DELETE_CITY_QUERY, paramMap);
		}catch (DataAccessException e) {
			throw new CityException("Error while deleting city: " + e.getMessage());
		}
	}

}
