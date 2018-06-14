package com.kamino.go.settings;

public class SystemEnvironmentSettingsProvider implements SettingsProvider {

	@Override
	public String get(String name) {
		return System.getenv(name);
	}

}
