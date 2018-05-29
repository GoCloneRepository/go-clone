package com.kamino.go.modules;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.AbstractModule;
import com.kamino.go.ServerProvider;
import com.kamino.go.ServletContextHandlerProvider;

public class ServerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Server.class).toProvider(ServerProvider.class);
		bind(ServletContextHandler.class).toProvider(ServletContextHandlerProvider.class);
	}
	
}
