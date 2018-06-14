package com.kamino.go.settings;

import java.util.Optional;

import com.google.inject.Inject;
import com.kamino.go.jms.settings.Jms;

public class SettingsImpl implements Settings {
	private SettingsProvider provider;
	
	@Inject
	public SettingsImpl(SettingsProvider provider) {
		this.provider = provider;
	}

	@Override
	public Rest rest() {
		return new Rest() {
			
			@Override
			public int port() {
				return getInt("REST_PORT", 8080);
			}
		};
	}

	@Override
	public Jms jms() {
		return new Jms() {
			
			@Override
			public String topic() {
				return getString("JMS_TOPIC", "topic");
			}
			
			@Override
			public int port() {
				return getInt("JMS_PORT", 61616);
			}
			
			@Override
			public String host() {
				return getString("JMS_HOST", "localhost");
			}
		};
	}

	private String getString(String name, String def) {
		return Optional.ofNullable(provider.get(name))
					   .orElse(def);
	}
	
	private int getInt(String name, int def) {
		return Optional.ofNullable(provider.get(name))
					   .map(v -> Integer.parseInt(v))
					   .orElse(def);
	}
}
