package com.kamino.clone.test.jms;

import javax.jms.*;

import org.junit.Test;

import com.kamino.go.jms.JmsService;
import com.kamino.go.jms.JmsServiceBuilder;
import com.kamino.go.jms.message.MessageHandler;

/**
 * For testing jms messages
 */
public class TestJmsServiceBuilder {

	@Test
	public void buildsJmsService() {
		JmsService service = new JmsServiceBuilder().withJmsSettings(new LocalJms())
													.withMessageHandler(new SimpleMessageHandler())
													.build();
		try {
			service.start();
			Thread.currentThread().join();
		} catch (InterruptedException e) { }
		finally {
			service.stop();
		}
	}
	
	private class SimpleMessageHandler implements MessageHandler {

		@Override
		public void onMessage(Message message) {
			try {
				System.out.println(((TextMessage)message).getText());
			} catch (JMSException e) {
				System.out.println(message);
			}
		}
		
	}
	
}
