package com.kamino.go.modules;

import com.google.inject.AbstractModule;
import com.kamino.go.settings.*;

public class SettingsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SettingsProvider.class).to(SystemEnvironmentSettingsProvider.class);
		bind(Settings.class).to(SettingsImpl.class);
	}
	
}
