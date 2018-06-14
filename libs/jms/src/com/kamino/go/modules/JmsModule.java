package com.kamino.go.modules;

import javax.jms.Connection;

import com.google.inject.AbstractModule;
import com.kamino.go.jms.activemq.ActiveMQConnectionProvider;

public class JmsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Connection.class).toProvider(ActiveMQConnectionProvider.class);
	}
	
}
