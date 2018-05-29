package com.kamino.go.test.startup;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.startup.*;

@RunWith(MockitoJUnitRunner.class)
public class TestStartupTaskProvider {
	@Mock private JmsServiceStartupTask jmsServiceStartupTask;
	@Mock private RestServerStartupTask restServerStartupTask;

	@InjectMocks
	private StartupTaskProvider provider;

	private StartupTaskProvider getProvider() {
		return provider;
	}
	
	@Test
	public void providesJmsServiceStartupTask() {
		assertProvides(JmsServiceStartupTask.class);
	}
	
	@Test
	public void providesRestServerStartupTask() {
		assertProvides(RestServerStartupTask.class);
	}
	
	private <T> void assertProvides(Class<T> cl) {
		assertThat(Stream.of(getProvider().get())
						 .filter(task -> cl.isAssignableFrom(task.getClass()))
						 .findFirst().isPresent(), is(true));
	}
}
