package com.kamino.go.modules;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;

public class DynamicModule extends AbstractModule {
	private List<Runnable> bindings = new ArrayList<>();	

	@Override
	protected void configure() {
		bindings.forEach(Runnable::run);
	}
	
	public <T> void addBinding(Class<T> cl, T instance) {
		bindings.add(() -> bind(cl).toInstance(instance));
	}
	
}
