package com.kamino.go.test.rest.event;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;

import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.event.RequestMessageBody;
import com.kamino.go.rest.filter.RequestName;

@RunWith(MockitoJUnitRunner.class)
public class TestRequestMessageBody {
	@Mock private RequestName requestName;
	@Mock private ContainerRequest requestContext;
	
	@Mock private Form form;
	@Mock private ExtendedUriInfo uriInfo;
	
	@InjectMocks
	private RequestMessageBody body;
	
	@Before
	public void setup() {
		when(requestContext.readEntity(eq(Form.class))).thenReturn(form);
		when(form.asMap()).thenReturn(new MultivaluedHashMap<>());
		
		when(requestContext.getUriInfo()).thenReturn(uriInfo);
		when(uriInfo.getPathParameters()).thenReturn(new MultivaluedHashMap<>());
	}

	private RequestMessageBody getBody() {
		return body;
	}

	@Test
	public void getName_isRequestName_value() {
		when(requestName.value()).thenReturn("name");
		
		String result = getBody().getName();
		
		assertThat(result, is(equalTo("name")));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getBody_isMap_withBody_fromContainerRequest() {
		MultivaluedHashMap<String, String> formMap = new MultivaluedHashMap<>();
		formMap.put("test", Arrays.asList("val1", "val2"));
		when(form.asMap()).thenReturn(formMap);
		
		Map<String, Object> body = getBody().getBody();
		
		assertThat((Map<String, Object>)body.get("body"), hasEntry("test", Arrays.asList("val1", "val2")));
	}
	
	@Test
	public void getBody_buffersEntity() {
		getBody().getBody();

		verify(requestContext).bufferEntity();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getBody_isMap_withPathParams_fromUriInfo() {
		MultivaluedHashMap<String, String> paramsMap = new MultivaluedHashMap<>();
		paramsMap.put("test", Arrays.asList("val1", "val2"));
		when(uriInfo.getPathParameters()).thenReturn(paramsMap);

		Map<String, Object> body = getBody().getBody();

		assertThat((Map<String, Object>)body.get("pathParameters"), hasEntry("test", Arrays.asList("val1", "val2")));
	}
}
