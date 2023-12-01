package com.vojislavmilenkovic.cityinfoapi.repo;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;

@FunctionalInterface
public interface GetCityByIdRepository {
	CityDTO getCityById(int id);
}
