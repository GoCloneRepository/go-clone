package com.kamino.clone.test.jms.activemq;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.junit.Test;

import com.kamino.clone.jms.activemq.ActiveMQConnectionProvider;
import com.kamino.clone.jms.settings.Jms;

public class TestActiveMQConnectionProvider {
	
	@Test
	public void connection() throws JMSException {
		ActiveMQConnectionProvider provider = new ActiveMQConnectionProvider(new LocalJms());
		
		AtomicBoolean execeptionThrown = new AtomicBoolean(false);
		Connection connection = provider.get();
		connection.setExceptionListener(new ExceptionListener() {
			
			@Override
			public void onException(JMSException exception) {
				execeptionThrown.set(true);
			}
		});
		connection.start();
		connection.stop();
		
		assertThat(execeptionThrown.get(), is(false));
	}
	
	private class LocalJms implements Jms {

		@Override
		public String topic() {
			return "topic";
		}

		@Override
		public String host() {
			return "localhost";
		}

		@Override
		public int port() {
			return 61616;
		}
		
	}
}
