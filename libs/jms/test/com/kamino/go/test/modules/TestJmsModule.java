package com.kamino.go.test.modules;

import static org.mockito.Mockito.mock;

import javax.jms.Connection;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.kamino.go.jms.settings.Jms;
import com.kamino.go.modules.JmsModule;
import com.kamino.go.modules.ModuleTest;

public class TestJmsModule extends ModuleTest {

	@Override
	protected Module[] getModules() {
		return new Module[] {
				new JmsModule(),
				new TestModule()
		};
	}
	
	@Test
	public void providesConnection() {
		assertProvides(Connection.class);
	}

	private class TestModule extends AbstractModule {
		
		@Override
		protected void configure() {
			bind(Jms.class).toInstance(mock(Jms.class));
		}
		
	}
}
