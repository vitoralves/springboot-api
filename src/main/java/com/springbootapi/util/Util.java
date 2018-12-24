package com.springbootapi.util;

import java.io.IOException;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	/**
	 * Recebe um ip como parâmetro e retorna um objeto contendo a latitude na
	 * posição 0 e a longitude na posição 1
	 * 
	 * @param ip
	 * @return
	 */
	public static Object[] getCoordinatesFromIp(String ip) {
		Object[] obj = { "", "" };
		try {
			String uri = "https://ipvigilante.com/" + ip;

			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			ObjectMapper mapper = new ObjectMapper();

			JsonNode actualObj = mapper.readTree(result);

			JsonNode innerNode = actualObj.get("data"); // Get the only element in the root node
			// get an element in that node
			JsonNode latitude = innerNode.get("latitude");
			JsonNode longitude = innerNode.get("longitude");

			obj[0] = latitude.asText();
			obj[1] = longitude.asText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Retorna id de posição na terra passando latitude e longitude da localização
	 * 
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static String getPositionOnEarthByCoordinates(String lat, String lon) {
		try {
			String uri = "https://www.metaweather.com/api/location/search/?lattlong=" + lat + "," + lon;

			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(result);
			
			List<String> l = actualObj.findValuesAsText("woeid");

			return l.get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	
	public static Object[] getWheater(String woeid) {
		Object[] obj = {"", ""};
		
		try {
			String uri = "https://www.metaweather.com/api/location/" + woeid;
			
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(result);

			JsonNode firstNode = actualObj.get("consolidated_weather").get(0);
			
			obj[0] = firstNode.get("max_temp");
			obj[1] = firstNode.get("min_temp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
