package com.kamino.go;

import java.util.stream.Stream;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kamino.go.resources.DefaultResourceProvider;
import com.kamino.go.resources.ResourceProvider;

public class ServletContextHandlerProvider implements Provider<ServletContextHandler> {
	private ResourceProvider resourceProvider;
	private DefaultResourceProvider defaultResourceProvider;
	
	@Inject
	public ServletContextHandlerProvider(
			ResourceProvider resourceProvider,
			DefaultResourceProvider defaultResourceProvider) {
		this.resourceProvider = resourceProvider;
		this.defaultResourceProvider = defaultResourceProvider;
	}

	private ResourceProvider getResourceProvider() {
		return resourceProvider;
	}

	private DefaultResourceProvider getDefaultResourceProvider() {
		return defaultResourceProvider;
	}

	@Override
	public ServletContextHandler get() {
        ResourceConfig config = new ResourceConfig();
        registerCompontents(config);
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));
        
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);  
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/*");
		return contextHandler;
	}

	private void registerCompontents(ResourceConfig config) {
		register(config, getResourceProvider());
		register(config, getDefaultResourceProvider());
	}
	
	private void register(ResourceConfig config, Provider<Object[]> provider) {
		Stream.of(provider.get())
			  .forEach(r -> config.register(r));
	}
}
