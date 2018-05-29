package com.kamino.go.test.shutdown;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.RestServer;
import com.kamino.go.shutdown.RestServerShutdownTask;

@RunWith(MockitoJUnitRunner.class)
public class TestRestServerShutdownTask {
	@Mock private RestServer server;
	
	@InjectMocks
	private RestServerShutdownTask task;

	private RestServerShutdownTask getTask() {
		return task;
	}
	
	@Test
	public void execute_destroysRestServer()  {
		getTask().execute();
		
		verify(server).destroy();
	}
	
}
