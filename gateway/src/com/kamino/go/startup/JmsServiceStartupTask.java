package com.kamino.go.startup;

import com.google.inject.Inject;
import com.kamino.go.jms.JmsService;
import com.kamino.go.server.startup.StartupTask;

public class JmsServiceStartupTask implements StartupTask {
	private JmsService service;
	
	@Inject
	public JmsServiceStartupTask(JmsService service) {
		this.service = service;
	}

	private JmsService getService() {
		return service;
	}

	@Override
	public void execute() {
		getService().start();
	}

}
