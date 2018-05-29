package com.kamino.go.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.kamino.go.jms.JmsService;
import com.kamino.go.jms.JmsServiceProvider;

public class JmsServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(JmsService.class).toProvider(JmsServiceProvider.class).in(Singleton.class);
	}
	
}
