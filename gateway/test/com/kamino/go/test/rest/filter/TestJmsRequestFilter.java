package com.kamino.go.test.rest.filter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.JmsService;
import com.kamino.go.rest.filter.JmsRequestFilter;
import com.kamino.go.rest.filter.RequestName;

@RunWith(MockitoJUnitRunner.class)
public class TestJmsRequestFilter {
	@Mock private JmsService jmsService;
	@Mock private ResourceInfo resourceInfo;
	
	@Mock private ContainerRequestContext requestContext;

	@InjectMocks
	private JmsRequestFilter filter;
	
	@Before
	public void setup() {
		filter.setResourceInfo(resourceInfo);
	}

	private JmsRequestFilter getFilter() {
		return filter;
	}
	
	@Test
	public void filter_postsEvent_whenMethodHasName() throws Exception {
		mockResourceMethod("methodWithName");
		
		getFilter().filter(requestContext);
		
		verify(jmsService).post(any());
	}
	
	@Test
	public void filter_doesntPostEvent_whenMethodHasNoName() throws Exception {
		mockResourceMethod("methodWithoutName");
		
		getFilter().filter(requestContext);
		
		verify(jmsService, never()).post(any());
	}
	
	private void mockResourceMethod(String name) throws Exception {
		when(resourceInfo.getResourceMethod()).thenReturn(TestResource.class.getMethod(name));
	}
	
	private interface TestResource {
		
		@RequestName("name")
		public void methodWithName();
		
		public void methodWithoutName();
		
		
	}
}
