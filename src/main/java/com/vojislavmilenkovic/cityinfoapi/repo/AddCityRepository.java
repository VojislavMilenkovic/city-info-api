package com.vojislavmilenkovic.cityinfoapi.repo;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;

@FunctionalInterface
public interface AddCityRepository {
	void addCity(CityDTO city);
}
