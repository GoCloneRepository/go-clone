package com.kamino.go.modules;

import com.google.inject.AbstractModule;
import com.kamino.go.server.shutdown.ShutdownTask;
import com.kamino.go.server.startup.StartupTask;
import com.kamino.go.shutdown.ShutdownTaskProvider;
import com.kamino.go.startup.StartupTaskProvider;

public class ServerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(StartupTask[].class).toProvider(StartupTaskProvider.class);
		bind(ShutdownTask[].class).toProvider(ShutdownTaskProvider.class);
	}
	
}
