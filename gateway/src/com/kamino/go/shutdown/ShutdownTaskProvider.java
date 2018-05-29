package com.kamino.go.shutdown;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kamino.go.server.shutdown.ShutdownTask;

public class ShutdownTaskProvider implements Provider<ShutdownTask[]> {
	private JmsServiceShutdownTask jmsServiceShutdownTask;
	private RestServerShutdownTask restServerShutdownTask;

	@Inject
	public ShutdownTaskProvider(
			JmsServiceShutdownTask jmsServiceShutdownTask,
			RestServerShutdownTask restServerShutdownTask) {
		this.jmsServiceShutdownTask = jmsServiceShutdownTask;
		this.restServerShutdownTask = restServerShutdownTask;
	}
	
	@Override
	public ShutdownTask[] get() {
		return new ShutdownTask[] {
			jmsServiceShutdownTask,
			restServerShutdownTask
		};
	}

}
