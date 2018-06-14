package com.kamino.go.test.filter;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.filter.CorsFilter;

@RunWith(MockitoJUnitRunner.class)
public class TestCorsFilter {
	@Mock private ContainerRequestContext requestContext;
	@Mock private ContainerResponseContext responseContext;
	@Mock private MultivaluedMap<String, Object> headers;
	
	@InjectMocks
	private CorsFilter filter;
	
	@Before
	public void setup() {
		when(responseContext.getHeaders()).thenReturn(headers);
	}

	private CorsFilter getFilter() {
		return filter;
	}
	
	@Test
	public void filter_setsOriginHeader() throws IOException {
		getFilter().filter(requestContext, responseContext);
		
		verify(headers).add(eq("Access-Control-Allow-Origin"), eq("*"));
	}
	
	@Test
	public void filter_setsCredentialsHeader() throws IOException {
		getFilter().filter(requestContext, responseContext);
		
		verify(headers).add(eq("Access-Control-Allow-Credentials"), eq("true"));
	}
	
	@Test
	public void filter_setsHeadersHeader() throws IOException {
		getFilter().filter(requestContext, responseContext);
		
		verify(headers).add(eq("Access-Control-Allow-Headers"), eq("origin, content-type, accept, authorization"));
	}
	
	@Test
	public void filter_setsMethodsHeader() throws IOException {
		getFilter().filter(requestContext, responseContext);
		
		verify(headers).add(eq("Access-Control-Allow-Methods"), eq("GET, POST, PUT, DELETE, OPTIONS, HEAD"));
	}
}
