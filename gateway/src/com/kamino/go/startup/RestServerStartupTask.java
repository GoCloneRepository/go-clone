package com.kamino.go.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.kamino.go.RestServer;
import com.kamino.go.server.startup.StartupTask;

public class RestServerStartupTask implements StartupTask {
	private static final Logger log = LoggerFactory.getLogger(RestServerStartupTask.class);
	
	private RestServer server;
	
	@Inject
	public RestServerStartupTask(RestServer server) {
		this.server = server;
	}
	
	private RestServer getServer() {
		return server;
	}

	@Override
	public void execute() {
		try {
			getServer().start();
		} catch (Exception e) {
			log.error("Failed to start rest server", e);
		}
	}

}
