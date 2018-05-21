package com.kamino.go.test.json;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kamino.go.json.JSONMapper;

public class TestJSONMapper {
	
	@Test
	public void map_createsJSONString() throws JsonProcessingException {
		JSONMapper mapper = new JSONMapper();
		
		String result = mapper.map(new TestDto("benji", 5));
		
		assertThat(result, is(equalTo("{\"name\":\"benji\",\"number\":5}")));
	}

	private class TestDto {
		private String name;
		private int number;
		
		public TestDto(String name, int number) {
			this.name = name;
			this.number = number;
		}

		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}

		@SuppressWarnings("unused")
		public int getNumber() {
			return number;
		}

		@SuppressWarnings("unused")
		public void setNumber(int number) {
			this.number = number;
		}
		
	}
}
