package com.kamino.go.rest;

import com.google.inject.Inject;
import com.kamino.go.resources.ResourceProvider;
import com.kamino.go.rest.filter.JmsRequestFilter;
import com.kamino.go.rest.resources.EchoResource;

public class ResourceProviderImpl implements ResourceProvider {
	private Object[] resources;
	
	@Inject
	public ResourceProviderImpl(
			// resources
			EchoResource echoResource,
			// filters
			JmsRequestFilter JmsRequestFilter) {
		resources = new Object[] {
			echoResource,
			JmsRequestFilter
		};
	}

	@Override
	public Object[] get() {
		return resources;
	}

}
