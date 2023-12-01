package com.vojislavmilenkovic.cityinfoapi.repo.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;
import com.vojislavmilenkovic.cityinfoapi.exceptions.CityException;
import com.vojislavmilenkovic.cityinfoapi.repo.UpdateCityRepository;
import com.vojislavmilenkovic.cityinfoapi.utils.CityUtil;

@Repository
public class UpdateCityRepositoryImpl implements UpdateCityRepository {

	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	private static final String UPDATE_CITY_QUERY = CityUtil.slurp("sql/update_city_query.sql");

	public UpdateCityRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		super();
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	@Override
	public void updateCity(int id, CityDTO city) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		paramMap.addValue("name", city.getName().trim());
		paramMap.addValue("country", city.getCountry().trim());
		paramMap.addValue("state", city.getState().trim());
		paramMap.addValue("region", city.getRegion().trim());
		paramMap.addValue("population", city.getPopulation().trim());

		try {
			namedJdbcTemplate.update(UPDATE_CITY_QUERY, paramMap);
		} catch (DataAccessException e) {
			throw new CityException("Error while trying to update City: " + e.getMessage());
		}
	}

}
