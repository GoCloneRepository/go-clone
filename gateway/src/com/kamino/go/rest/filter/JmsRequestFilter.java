package com.kamino.go.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.google.inject.Inject;
import com.kamino.go.jms.JmsService;
import com.kamino.go.jms.event.RequestEvent;

@Provider
public class JmsRequestFilter implements ContainerRequestFilter {
	private JmsService jmsService;
	
	@Context
	private ResourceInfo resourceInfo;
	
	@Inject
	public JmsRequestFilter(JmsService jmsService) {
		this.jmsService = jmsService;
	}

	private JmsService getJmsService() {
		return jmsService;
	}
	
	// For unit tests
	public void setResourceInfo(ResourceInfo resourceInfo) {
		this.resourceInfo = resourceInfo;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		RequestName requestName = resourceInfo.getResourceMethod()
											  .getAnnotation(RequestName.class);
		if(requestName != null) {
			getJmsService().post(new RequestEvent(requestName, requestContext));
		}
	}

}
