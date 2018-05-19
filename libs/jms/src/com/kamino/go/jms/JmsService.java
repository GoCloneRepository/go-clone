package com.kamino.go.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.kamino.clone.jms.event.Event;
import com.kamino.clone.jms.message.MessageCodec;
import com.kamino.clone.jms.settings.Jms;

public class JmsService {
	private static final Logger log = LoggerFactory.getLogger(JmsService.class);
	
	private Jms jms;
	private Connection connection;
	private MessageCodec codec;
	
	@Inject
	public JmsService(Jms jms, Connection connection, MessageCodec codec) {
		this.jms = jms;
		this.connection = connection;
		this.codec = codec;
	}
	
	private Jms jms() {
		return jms;
	}

	private Connection getConnection() {
		return connection;
	}

	private MessageCodec getCodec() {
		return codec;
	}

	public void start() {
		try {
			getConnection().start();
		} catch (JMSException e) {
			log.error("Failed to start jms connection");
			throw new RuntimeException(e);
		}
		
		// TODO create consumer
	}

	public void stop() {
		try {
			getConnection().close();
		} catch (JMSException e) {
			log.error("Failed to close jms connection");
			throw new RuntimeException(e);
		}
	}
	
	public void post(Event event) {
		log.debug("Posting event: {}", event);
		Message message = getCodec().createMessage(event);
		try {
			createProducer().send(message);
		} catch (JMSException e) {
			log.error(String.format("Failed to send message %s", message), e);
		}
	}
	
	private Session createSession() throws JMSException {
		return getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	private Destination createTopic() throws JMSException {
		return createSession().createTopic(jms().topic());
	}
	
	private MessageProducer createProducer() throws JMSException {
		return createSession().createProducer(createTopic());
	}

}
