package com.kamino.go.test.startup;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.JmsService;
import com.kamino.go.startup.JmsServiceStartupTask;

@RunWith(MockitoJUnitRunner.class)
public class TestJmsServiceStartupTask {
	@Mock private JmsService service;
	
	@InjectMocks
	private JmsServiceStartupTask task;

	private JmsServiceStartupTask getTask() {
		return task;
	}
	
	@Test
	public void execute_startsService() {
		getTask().execute();
		
		verify(service).start();
	}
}
