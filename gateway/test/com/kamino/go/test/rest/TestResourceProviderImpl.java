package com.kamino.go.test.rest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.rest.ResourceProviderImpl;
import com.kamino.go.rest.filter.JmsRequestFilter;
import com.kamino.go.rest.resources.EchoResource;

@RunWith(MockitoJUnitRunner.class)
public class TestResourceProviderImpl {
	@Mock private EchoResource echoResource;

	@InjectMocks
	private ResourceProviderImpl provider;
	
	private ResourceProviderImpl getProvider() {
		return provider;
	}
	
	@Test
	public void providesEchoResource() {
		assertProvides(EchoResource.class);
	}
	
	@Test
	public void providesJmsRequestFilter() {
		assertProvides(JmsRequestFilter.class);
	}

	private <T> void assertProvides(Class<T> cl) {
		assertThat(Stream.of(getProvider().get())
						 .filter(resource -> cl.isAssignableFrom(resource.getClass()))
						 .findFirst().isPresent(), is(true));
	}
}
