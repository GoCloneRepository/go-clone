package com.kamino.go.jms;

import com.google.inject.Guice;
import com.google.inject.Module;
import com.kamino.go.jms.message.MessageHandler;
import com.kamino.go.jms.settings.Jms;
import com.kamino.go.modules.DynamicModule;
import com.kamino.go.modules.JmsModule;
import com.kamino.go.modules.MessageModule;

public class JmsServiceBuilder {
	private DynamicModule dynamicModule;
	
	public JmsServiceBuilder() {
		dynamicModule = new DynamicModule();
	}
	
	public JmsServiceBuilder withMessageHandler(MessageHandler messageHandler) {
		dynamicModule.addBinding(MessageHandler.class, messageHandler);
		return this;
	}
	
	public JmsServiceBuilder withJmsSettings(Jms jms) {
		dynamicModule.addBinding(Jms.class, jms);
		return this;
	}
	
	public JmsService build() {
		return Guice.createInjector(getModules())
					.getInstance(JmsService.class);
	}
	
	private Module[] getModules() {
		return new Module[] {
				new JmsModule(),
				new MessageModule(),
				dynamicModule
		};
	}
}
