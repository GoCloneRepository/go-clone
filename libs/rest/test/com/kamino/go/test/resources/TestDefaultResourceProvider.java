package com.kamino.go.test.resources;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.kamino.go.resources.DefaultResourceProvider;

public class TestDefaultResourceProvider {
	private DefaultResourceProvider provider;
	
	@Before
	public void setup() {
		provider = new DefaultResourceProvider();
	}
	
	private DefaultResourceProvider getProvider() {
		return provider;
	}
	
	@Test
	public void providesJacksonJsonProvider() {
		assertProvides(JacksonJsonProvider.class);
	}

	private void assertProvides(Class<?> cl) {
		Optional<Object> oObj = Stream.of(getProvider().get())
								     .filter(o -> o.getClass().isAssignableFrom(cl))
								     .findFirst();
		
		assertThat(oObj.isPresent(), is(true));
	}
	
}
