package com.kamino.go.rest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kamino.go.RestServer;
import com.kamino.go.RestServerBuilder;
import com.kamino.go.resources.ResourceProvider;
import com.kamino.go.settings.Settings;

public class RestServerProvider implements Provider<RestServer> {
	Settings settings;
	ResourceProvider resourceProvider;
	
	@Inject
	public RestServerProvider(
			Settings settings,
			ResourceProvider resourceProvider) {
		this.settings = settings;
		this.resourceProvider = resourceProvider;
	}

	@Override
	public RestServer get() {
		return new RestServerBuilder()
				.withRestSettings(settings.rest())
				.withResourceProvider(resourceProvider)
				.build();
	}

}
