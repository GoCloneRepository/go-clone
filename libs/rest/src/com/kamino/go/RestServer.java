package com.kamino.go;

import org.eclipse.jetty.server.Server;

import com.google.inject.Inject;

public class RestServer {
	private Server server;
	
	@Inject
	public RestServer(Server server) {
		this.server = server;
	}

	private Server getServer() {
		return server;
	}
	
	public void start() throws Exception {
		getServer().start();
	}
	
	public void join() throws InterruptedException {
		getServer().join();
	}
	
	public void destroy() {
		getServer().destroy();
	}
}
