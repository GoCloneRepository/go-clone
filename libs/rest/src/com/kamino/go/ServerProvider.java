package com.kamino.go;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kamino.go.settings.Rest;

public class ServerProvider implements Provider<Server> {
	private Rest rest;
	private ServletContextHandler contextHandler;
	
	@Inject
	public ServerProvider(
			Rest rest,
			ServletContextHandler contextHandler) {
		this.rest = rest;
		this.contextHandler = contextHandler;
	}

	private Rest getRest() {
		return rest;
	}

	private ServletContextHandler getContextHandler() {
		return contextHandler;
	}

	@Override
	public Server get() {
		Server server = new Server(getRest().port());
		server.setHandler(getContextHandler());
		return server;
	}

}
