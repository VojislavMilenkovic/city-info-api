package com.vojislavmilenkovic.cityinfoapi.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;
import com.vojislavmilenkovic.cityinfoapi.dto.mapper.CityDTORowMapper;
import com.vojislavmilenkovic.cityinfoapi.exceptions.CityException;
import com.vojislavmilenkovic.cityinfoapi.repo.GetAllCitiesRepository;
import com.vojislavmilenkovic.cityinfoapi.utils.CityUtil;

@Repository
public class GetAllCitiesRepositoryImpl implements GetAllCitiesRepository {

	@Value("${spring.openweather.api.key}")
	private String apiKey;
	@Value("${spring.openweather.api.url}")
	private String apiUrl;

	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	private static final String GET_ALL_CITIES_QUERY = CityUtil.slurp("sql/get_all_cities_query.sql");

	public GetAllCitiesRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		super();
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	@Override
	public List<CityDTO> getAllCities() {
		try {
			List<CityDTO> cities = namedJdbcTemplate.query(GET_ALL_CITIES_QUERY, new CityDTORowMapper());

			for (CityDTO city : cities) {
				String temperature = CityUtil.getTemperatureForTheCity(city.getName(), apiKey, apiUrl);
				city.setTemp_c(temperature);
			}
			return cities;
		} catch (DataAccessException e) {
			throw new CityException("Error while getting list of cities " + e.getMessage());
		}
	}

}
