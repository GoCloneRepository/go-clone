package com.kamino.go.test.startup;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.RestServer;
import com.kamino.go.startup.RestServerStartupTask;

@RunWith(MockitoJUnitRunner.class)
public class TestRestServerStartupTask {
	@Mock private RestServer server;
	
	@InjectMocks
	private RestServerStartupTask task;

	private RestServerStartupTask getTask() {
		return task;
	}
	
	@Test
	public void execute_startsRestServer() throws Exception {
		getTask().execute();
		
		verify(server).start();
	}
	
}
