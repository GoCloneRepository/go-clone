package com.kamino.go.startup;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kamino.go.server.startup.StartupTask;

public class StartupTaskProvider implements Provider<StartupTask[]> {
	private JmsServiceStartupTask jmsServiceStartupTask;
	private RestServerStartupTask restServerStartupTask;

	@Inject
	public StartupTaskProvider(
			JmsServiceStartupTask jmsServiceStartupTask,
			RestServerStartupTask restServerStartupTask) {
		this.jmsServiceStartupTask = jmsServiceStartupTask;
		this.restServerStartupTask = restServerStartupTask;
	}

	@Override
	public StartupTask[] get() {
		return new StartupTask[] {
			jmsServiceStartupTask,
			restServerStartupTask
		};
	}

}
