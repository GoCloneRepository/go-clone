package com.kamino.go.resources;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Provider;
import com.kamino.go.filter.CorsFilter;

import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;

/**
 * Provides resource that are required by default
 * e.g: swagger, request filters, ...
 */
public class DefaultResourceProvider implements Provider<Object[]> {

	@Override
	public Object[] get() {
		return new Object[] {
			// automatic encoding/decoding of application/json
			new JacksonJsonProvider(),
			// swagger endpoint
			new AcceptHeaderOpenApiResource(),
			// sets cors headers
			new CorsFilter()
		};
	}

}
