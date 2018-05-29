package com.kamino.go.test.modules;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.kamino.go.RestServer;
import com.kamino.go.modules.*;
import com.kamino.go.resources.ResourceProvider;
import com.kamino.go.settings.Rest;
import com.kamino.go.settings.Settings;

@RunWith(MockitoJUnitRunner.class)
public class TestRestModule extends ModuleTest {
	@Mock private Settings settings;
	@Mock private Rest rest;
	
	@Before
	public void setup() {
		when(settings.rest()).thenReturn(rest);
	}

	@Override
	protected Module[] getModules() {
		return new Module[] {
			new RestModule(),
			new TestModule()
		};
	}
	
	@Test
	public void providesResourceProvider() {
		assertProvides(ResourceProvider.class);
	}
	
	@Test
	public void providesRestServer() {
		assertProvides(RestServer.class);
	}
	
	@Test
	public void restServer_isSingleton() {
		assertSingleton(RestServer.class);
	}

	private class TestModule extends AbstractModule {
		
		@Override
		protected void configure() {
			bind(Settings.class).toInstance(settings);
		}
		
	}

}
