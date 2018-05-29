package com.kamino.go.test.modules;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.kamino.go.jms.JmsService;
import com.kamino.go.jms.settings.Jms;
import com.kamino.go.modules.JmsServiceModule;
import com.kamino.go.modules.ModuleTest;
import com.kamino.go.settings.Settings;

@RunWith(MockitoJUnitRunner.class)
public class TestJmsServiceModule extends ModuleTest {
	@Mock private Settings settings;
	@Mock private Jms jms;
	
	@Before
	public void setup() {
		when(settings.jms()).thenReturn(jms);
	}

	@Override
	protected Module[] getModules() {
		return new Module[] {
			new JmsServiceModule(),
			new TestModule()
		};
	}
	
	@Test
	public void providesJmsService() {
		assertProvides(JmsService.class);
	}
	
	@Test
	public void jmsService_isSingleton() {
		assertSingleton(JmsService.class);
	}

	private class TestModule extends AbstractModule {
		
		@Override
		protected void configure() {
			bind(Settings.class).toInstance(settings);
		}
		
	}
}
