package com.vojislavmilenkovic.cityinfoapi.dto.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vojislavmilenkovic.cityinfoapi.dto.CityDTO;

public class CityDTOSerializer extends JsonSerializer<CityDTO> {

	@Override
	public void serialize(CityDTO cityDTO, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", cityDTO.getId());
		gen.writeStringField("name", cityDTO.getName());
		gen.writeStringField("country", cityDTO.getCountry());
		gen.writeStringField("state/region", cityDTO.getState()); // Combine state and region
		gen.writeStringField("population", cityDTO.getPopulation());
		gen.writeStringField("temp_c", cityDTO.getTemp_c());
		gen.writeEndObject();

	}

}
