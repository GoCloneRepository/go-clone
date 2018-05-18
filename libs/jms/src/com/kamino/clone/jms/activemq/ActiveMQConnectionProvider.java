package com.kamino.clone.jms.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.kamino.clone.jms.settings.Jms;

public class ActiveMQConnectionProvider implements Provider<Connection> {
	private static final Logger log = LoggerFactory.getLogger(ActiveMQConnectionProvider.class);
	
	private Jms jms;
	
	@Inject
	public ActiveMQConnectionProvider(Jms jms) {
		this.jms = jms;
	}

	private Jms jms() {
		return jms;
	}

	@Override
	public Connection get() {
		try {
			return createAndStart();
		} catch (JMSException e) {
			log.info("Failed to create activemq connection");
			throw new RuntimeException(e);
		}
	}

	private Connection createAndStart() throws JMSException {
		String url = createConnectionUrl();
		log.info("Creating ActiveMQ connection: {}", url);
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		Connection connection = factory.createConnection();
		connection.start();
		return connection;
	}
	
	private String createConnectionUrl() {
		return String.format("failover:tcp://%s:%d", jms().host(), jms().port());
	}
}
