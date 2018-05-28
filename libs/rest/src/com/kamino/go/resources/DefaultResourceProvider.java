package com.kamino.go.resources;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Provider;

/**
 * Provides resource that are required by default
 * e.g: swagger, request filters, ...
 */
public class DefaultResourceProvider implements Provider<Object[]> {

	@Override
	public Object[] get() {
		return new Object[] {
			new JacksonJsonProvider()
		};
	}

}
