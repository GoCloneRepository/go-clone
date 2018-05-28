package com.kamino.go;

import org.eclipse.jetty.server.Server;

import com.google.inject.Guice;
import com.google.inject.Module;
import com.kamino.go.modules.DynamicModule;
import com.kamino.go.modules.ServerModule;
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

	public Server build() {
		return Guice.createInjector(getModules())
					.getInstance(Server.class);
	}
	
	private Module[] getModules() {
		return new Module[] {
				dynamicModule,
				new ServerModule()
		};
	}
}
