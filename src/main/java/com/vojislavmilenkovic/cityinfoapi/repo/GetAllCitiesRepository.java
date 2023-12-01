package com.vojislavmilenkovic.cityinfoapi.repo;

import java.util.List;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;

@FunctionalInterface
public interface GetAllCitiesRepository {
	List<CityDTO> getAllCities();
}
