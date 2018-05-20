package com.kamino.go.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapper {
	private ObjectMapper objectMapper;

	public JSONMapper() {
		objectMapper = new ObjectMapper();
	}

	private ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	public String map(Object obj) throws JsonProcessingException {
		return getObjectMapper().writeValueAsString(obj);
	}
}
