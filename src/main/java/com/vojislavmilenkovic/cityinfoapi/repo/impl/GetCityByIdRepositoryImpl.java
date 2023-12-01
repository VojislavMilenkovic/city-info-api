package com.vojislavmilenkovic.cityinfoapi.repo.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;
import com.vojislavmilenkovic.cityinfoapi.dto.mapper.CityDTORowMapper;
import com.vojislavmilenkovic.cityinfoapi.exceptions.CityException;
import com.vojislavmilenkovic.cityinfoapi.repo.GetCityByIdRepository;
import com.vojislavmilenkovic.cityinfoapi.utils.CityUtil;

@Repository
public class GetCityByIdRepositoryImpl implements GetCityByIdRepository {
	
	@Value("${spring.openweather.api.key}")
	private String apiKey;
	@Value("${spring.openweather.api.url}")
	private String apiUrl;
	
	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	private static final String GET_CITY_BY_ID_QUERY = CityUtil.slurp("sql/get_city_by_id_query.sql");

	

	public GetCityByIdRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		super();
		this.namedJdbcTemplate = namedJdbcTemplate;
//		this.webClientBuilder = webClientBuilder;
	}

	

	@Override
	public CityDTO getCityById(int id) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);

		try {
			CityDTO city = namedJdbcTemplate.queryForObject(GET_CITY_BY_ID_QUERY, paramMap, new CityDTORowMapper());
			if (city != null) {
				String temperature = CityUtil.getTemperatureForTheCity(city.getName(), apiKey, apiUrl);
				city.setTemp_c(temperature);
			}

			return city;

		} catch (DataAccessException e) {
			throw new CityException("Error while getting city by id: " + e.getMessage());

		}

	}

}
