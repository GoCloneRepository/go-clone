package com.kamino.go.modules;

import com.google.inject.AbstractModule;
import com.kamino.go.jms.message.JSONMessageCodec;
import com.kamino.go.jms.message.MessageCodec;

public class MessageModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MessageCodec.class).to(JSONMessageCodec.class);
	}
	
}
