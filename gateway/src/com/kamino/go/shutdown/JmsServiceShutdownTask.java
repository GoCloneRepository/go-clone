package com.kamino.go.shutdown;

import com.google.inject.Inject;
import com.kamino.go.jms.JmsService;
import com.kamino.go.server.shutdown.ShutdownTask;

public class JmsServiceShutdownTask implements ShutdownTask {
	private JmsService service;
	
	@Inject
	public JmsServiceShutdownTask(JmsService service) {
		this.service = service;
	}
	
	private JmsService getService() {
		return service;
	}

	@Override
	public void execute() {
		getService().stop();
	}

}
