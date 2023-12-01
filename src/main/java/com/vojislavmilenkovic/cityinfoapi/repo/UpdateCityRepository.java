package com.vojislavmilenkovic.cityinfoapi.repo;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;

@FunctionalInterface
public interface UpdateCityRepository {
	void updateCity(int id, CityDTO city);
}
