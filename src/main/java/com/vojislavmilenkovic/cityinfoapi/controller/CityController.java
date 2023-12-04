package com.vojislavmilenkovic.cityinfoapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;
import com.vojislavmilenkovic.cityinfoapi.repo.AddCityRepository;
import com.vojislavmilenkovic.cityinfoapi.repo.DeleteCityRepository;
import com.vojislavmilenkovic.cityinfoapi.repo.GetAllCitiesRepository;
import com.vojislavmilenkovic.cityinfoapi.repo.GetCityByIdRepository;
import com.vojislavmilenkovic.cityinfoapi.repo.UpdateCityRepository;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

	private AddCityRepository addCityRepository;
	private GetCityByIdRepository getCityByIdRepository;
	private UpdateCityRepository updateCityRepository;
	private DeleteCityRepository deleteCityRepository;
	private GetAllCitiesRepository getAllCitiesRepository;

	public CityController(AddCityRepository addCityRepository, GetCityByIdRepository getCityByIdRepository,
			UpdateCityRepository updateCityRepository, DeleteCityRepository deleteCityRepository,
			GetAllCitiesRepository getAllCitiesRepository) {
		super();
		this.addCityRepository = addCityRepository;
		this.getCityByIdRepository = getCityByIdRepository;
		this.updateCityRepository = updateCityRepository;
		this.deleteCityRepository = deleteCityRepository;
		this.getAllCitiesRepository = getAllCitiesRepository;
	}

	@PostMapping("/addCity")
	public ResponseEntity<String> addCity(@RequestBody CityDTO city) {
		try {
			this.addCityRepository.addCity(city);
			return ResponseEntity.status(HttpStatus.CREATED).body("City " + city.getName() + " added");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while adding city: " + city.getName());
		}
	}

	@GetMapping("/getById")
	public ResponseEntity<CityDTO> addCity(@RequestParam int id) {
		try {
			CityDTO city = getCityByIdRepository.getCityById(id);
			return ResponseEntity.status(HttpStatus.OK).body(city);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/updateCity")
	public ResponseEntity<String> updateCity(@RequestParam int id, @RequestBody CityDTO city) {
		try {
			updateCityRepository.updateCity(id, city);
			return ResponseEntity.status(HttpStatus.OK).body("City with ID: " + id + " updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while trying to update the city with id: " + id);
		}
	}

	@DeleteMapping("/deleteCity")
	public ResponseEntity<String> deleteCity(@RequestParam int id) {

		try {
			deleteCityRepository.deleteCity(id);
			return ResponseEntity.status(HttpStatus.OK).body("City with ID: " + id + " is deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error while trying to delete the city with id: " + id);
		}
	}

	@GetMapping("/getAllCities")
	public ResponseEntity<List<CityDTO>> getAllCities() {
		try {
			List<CityDTO> cities = getAllCitiesRepository.getAllCities();
			return ResponseEntity.status(HttpStatus.OK).body(cities);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
