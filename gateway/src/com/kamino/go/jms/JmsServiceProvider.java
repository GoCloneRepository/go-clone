package com.kamino.go.jms;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kamino.go.jms.settings.Jms;
import com.kamino.go.settings.Settings;

public class JmsServiceProvider implements Provider<JmsService> {
	private Settings settings;
	
	@Inject
	public JmsServiceProvider(Settings settings) {
		this.settings = settings;
	}

	private Jms jms() {
		return settings.jms();
	}
	
	@Override
	public JmsService get() {
		return new JmsServiceBuilder()
				.withJmsSettings(jms())
				.build();
	}

}
