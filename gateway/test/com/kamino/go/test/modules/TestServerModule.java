package com.kamino.go.test.modules;

import org.junit.Test;

import com.google.inject.Module;
import com.kamino.go.modules.ModuleTest;
import com.kamino.go.modules.RestServerModule;
import com.kamino.go.server.shutdown.ShutdownTask;
import com.kamino.go.server.startup.StartupTask;

public class TestServerModule extends ModuleTest {

	@Override
	protected Module[] getModules() {
		return new Module[] {
			new RestServerModule()
		};
	}
	
	@Test
	public void providesStartupTasks() {
		assertProvides(StartupTask[].class);
	}
	
	@Test
	public void providesShutdownTasks() {
		assertProvides(ShutdownTask[].class);
	}

}
