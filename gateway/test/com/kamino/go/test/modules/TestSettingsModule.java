package com.kamino.go.test.modules;

import org.junit.Test;

import com.google.inject.Module;
import com.kamino.go.modules.ModuleTest;
import com.kamino.go.modules.SettingsModule;
import com.kamino.go.settings.Settings;
import com.kamino.go.settings.SettingsProvider;

public class TestSettingsModule extends ModuleTest {

	@Override
	protected Module[] getModules() {
		return new Module[] {
			new SettingsModule()
		};
	}
	
	@Test
	public void providesSettingsProvider() {
		assertProvides(SettingsProvider.class);
	}
	
	@Test
	public void providesSettings() {
		assertProvides(Settings.class);
	}

}
