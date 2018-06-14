package com.kamino.go.test.shutdown;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.shutdown.*;

@RunWith(MockitoJUnitRunner.class)
public class TestShutdownTaskProvider {
	@Mock private JmsServiceShutdownTask jmsServiceShutdownTask;
	@Mock private RestServerShutdownTask restServerShutdownTask;

	@InjectMocks
	private ShutdownTaskProvider provider;

	private ShutdownTaskProvider getProvider() {
		return provider;
	}
	
	@Test
	public void providesJmsServiceShutdownTask() {
		assertProvides(JmsServiceShutdownTask.class);
	}
	
	@Test
	public void providesRestServerShutdownTask() {
		assertProvides(JmsServiceShutdownTask.class);
	}
	
	private <T> void assertProvides(Class<T> cl) {
		assertThat(Stream.of(getProvider().get())
				 		 .filter(task -> cl.isAssignableFrom(task.getClass()))
						 .findFirst().isPresent(), is(true));
	}
}
