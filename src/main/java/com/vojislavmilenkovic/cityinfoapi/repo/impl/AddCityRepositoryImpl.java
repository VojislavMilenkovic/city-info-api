package com.vojislavmilenkovic.cityinfoapi.repo.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;
import com.vojislavmilenkovic.cityinfoapi.exceptions.CityException;
import com.vojislavmilenkovic.cityinfoapi.repo.AddCityRepository;
import com.vojislavmilenkovic.cityinfoapi.utils.CityUtil;

@Repository
public class AddCityRepositoryImpl implements AddCityRepository {

	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	private static final String ADD_CITY_QUERY = CityUtil.slurp("sql/add_city_query.sql");

	public AddCityRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		super();
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	@Override
	public void addCity(CityDTO city) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", city.getName().trim());
		paramMap.addValue("country", city.getCountry().trim());
		paramMap.addValue("state", city.getState().trim());
		paramMap.addValue("region", city.getRegion().trim());
		paramMap.addValue("population", city.getPopulation().trim());

		try {
			namedJdbcTemplate.update(ADD_CITY_QUERY, paramMap);
		} catch (DataAccessException e) {
			throw new CityException("Error while inserting city: " + e.getMessage());
		}

	}

}
