package com.kamino.go.test.modules;

import org.junit.Test;

import com.google.inject.Module;
import com.kamino.go.jms.message.MessageCodec;
import com.kamino.go.modules.MessageModule;
import com.kamino.go.modules.ModuleTest;

public class TestMessageModule extends ModuleTest {

	@Override
	protected Module[] getModules() {
		return new Module[] {
				new MessageModule()
		};
	}

	@Test
	public void providesMessageCodec() {
		assertProvides(MessageCodec.class);
	}
}
