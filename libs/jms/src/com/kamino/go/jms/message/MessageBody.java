package com.kamino.go.jms.message;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface MessageBody {

	@JsonProperty("name")
	public String getName();

	@JsonProperty("body")
	public Map<String, Object> getBody();
	
}
