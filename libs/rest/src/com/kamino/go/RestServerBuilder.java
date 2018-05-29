package com.kamino.go;

import com.google.inject.Guice;
import com.google.inject.Module;
import com.kamino.go.modules.DynamicModule;
import com.kamino.go.modules.RestServerModule;
import com.kamino.go.resources.ResourceProvider;
import com.kamino.go.settings.Rest;


public class RestServerBuilder {
	private DynamicModule dynamicModule;
	
	public RestServerBuilder() {
		dynamicModule = new DynamicModule();
	}
	
	public RestServerBuilder withRestSettings(Rest rest) {
		dynamicModule.addBinding(Rest.class, rest);
		return this;
	}
	
	public RestServerBuilder withResourceProvider(ResourceProvider resourceProvider) {
		dynamicModule.addBinding(ResourceProvider.class, resourceProvider);
		return this;
	}

	public RestServer build() {
		return Guice.createInjector(getModules())
					.getInstance(RestServer.class);
	}
	
	private Module[] getModules() {
		return new Module[] {
				dynamicModule,
				new RestServerModule()
		};
	}
}
