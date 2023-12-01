package com.vojislavmilenkovic.cityinfoapi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vojislavmilenkovic.cityinfoapi.dto.serialize.CityDTOSerializer;

@JsonSerialize(using = CityDTOSerializer.class)
public class CityDTO implements Serializable {

	private static final long serialVersionUID = 4083517660267756199L;
	private int id;
	private String name;
	private String country;
	private String state;
	private String region;
	private String population;
	private String temp_c;

	public CityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CityDTO(int id, String name, String country, String state, String region, String population, String temp_c) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.state = state;
		this.region = region;
		this.population = population;
		this.temp_c = temp_c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getTemp_c() {
		return temp_c;
	}

	public void setTemp_c(String temp_c) {
		this.temp_c = temp_c;
	}

}
