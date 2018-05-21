package com.kamino.clone.test.jms;

import org.junit.Test;

import com.kamino.go.jms.JmsService;
import com.kamino.go.jms.JmsServiceBuilder;

public class TestJmsServiceBuilder {

	@Test
	public void buildsJmsService() {
		JmsService service = new JmsServiceBuilder().withJmsSettings(new LocalJms())
													.build();
		try {
			service.start();
		}
		finally {
			service.stop();
		}
	}
	
}
