package com.kamino.clone.test.jms.activemq;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.junit.Test;

import com.kamino.clone.test.jms.LocalJms;
import com.kamino.go.jms.activemq.ActiveMQConnectionProvider;

public class TestActiveMQConnectionProvider {
	
	@Test
	public void providedConnectionStartAndStops() throws JMSException {
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
	
}
