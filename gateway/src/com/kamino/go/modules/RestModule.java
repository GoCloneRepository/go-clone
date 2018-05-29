package com.kamino.go.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.kamino.go.RestServer;
import com.kamino.go.resources.ResourceProvider;
import com.kamino.go.rest.ResourceProviderImpl;
import com.kamino.go.rest.RestServerProvider;

public class RestModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ResourceProvider.class).to(ResourceProviderImpl.class);
		bind(RestServer.class).toProvider(RestServerProvider.class).in(Singleton.class);
	}
	
}
