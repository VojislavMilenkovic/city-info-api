package com.vojislavmilenkovic.cityinfoapi.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vojislavmilenkovic.cityinfoapi.exceptions.CityException;

public class CityUtil {
	
	private static final WebClient.Builder webClientBuilder = WebClient.builder();
	
	//Helper method to read sql files
	public static final String slurp(String filename) {
		try {
			return Files.readString(
					Paths.get(Thread.currentThread().getContextClassLoader().getResource(filename).toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	// Helper method to get temperature for the specific city
		public static String getTemperatureForTheCity(String cityName, String apiKey, String apiUrl) {
			ObjectMapper objectMapper = new ObjectMapper();

			String requestUrl = apiUrl.replace("{city}", cityName).replace("{apiKey}", apiKey);
			WebClient webClient = webClientBuilder.baseUrl(requestUrl).build();
			try {
				String response = webClient.get().retrieve().bodyToMono(String.class).block();
				
				try {
					JsonNode jsonNode = objectMapper.readTree(response);
					JsonNode mainNode = jsonNode.get("main");
					return Integer.toString(mainNode.get("temp").asInt());
				} catch (Exception e) {
					e.getMessage();
					return "N/A";
				}
				
			}catch(CityException e) {
				throw new CityException("Error while contacting weather service: " + e.getMessage());
			}

			
		}
	
}
