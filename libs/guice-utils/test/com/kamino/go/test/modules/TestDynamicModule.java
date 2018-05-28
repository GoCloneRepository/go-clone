package com.kamino.go.test.modules;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.kamino.go.modules.DynamicModule;

public class TestDynamicModule {

	@Test
	public void addBinding_bindsInstance() {
		DynamicModule module = new DynamicModule();
		module.addBinding(String.class, "test");
		Injector injector = Guice.createInjector(new Module[] { module });
		
		String result = injector.getInstance(String.class);
		
		assertThat(result, is(equalTo("test")));
	}
	
}
