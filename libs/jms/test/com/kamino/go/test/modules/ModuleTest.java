package com.kamino.go.test.modules;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public abstract class ModuleTest {
	private Injector injector;
	
	@Before
	public void setupInjector() {
		injector = Guice.createInjector(getModules());
	}
	
	private Injector getInjector() {
		return injector;
	}

	protected <T> T get(Class<T> cl) {
		return getInjector().getInstance(cl);
	}
	
	protected <T> void assertProvides(Class<T> cl) {
		assertThat(get(cl), is(not(nullValue())));
	}

	protected abstract Module[] getModules();
}
