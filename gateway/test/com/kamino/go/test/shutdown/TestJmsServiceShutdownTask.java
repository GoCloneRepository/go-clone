package com.kamino.go.test.shutdown;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.JmsService;
import com.kamino.go.shutdown.JmsServiceShutdownTask;

@RunWith(MockitoJUnitRunner.class)
public class TestJmsServiceShutdownTask {
	@Mock private JmsService service;
	
	@InjectMocks
	private JmsServiceShutdownTask task;

	private JmsServiceShutdownTask getTask() {
		return task;
	}
	
	@Test
	public void execute_stopsService() {
		getTask().execute();
		
		verify(service).stop();
	}
}
