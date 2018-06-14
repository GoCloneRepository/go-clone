package com.kamino.go.shutdown;

import com.google.inject.Inject;
import com.kamino.go.RestServer;
import com.kamino.go.server.shutdown.ShutdownTask;

public class RestServerShutdownTask implements ShutdownTask {
	private RestServer server;
	
	@Inject
	public RestServerShutdownTask(RestServer server) {
		this.server = server;
	}
	
	private RestServer getServer() {
		return server;
	}

	@Override
	public void execute() {
		getServer().destroy();
	}

}
