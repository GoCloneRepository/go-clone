package com.kamino.go.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.kamino.go.jms.event.Event;
import com.kamino.go.jms.message.MessageCodec;
import com.kamino.go.jms.message.MessageHandler;
import com.kamino.go.jms.settings.Jms;

public class JmsService implements MessageListener {
	private static final Logger log = LoggerFactory.getLogger(JmsService.class);
	
	private Jms jms;
	private Connection connection;
	private MessageCodec codec;
	private MessageHandler messageHandler;
	
	@Inject
	public JmsService(
			Jms jms, 
			Connection connection, 
			MessageCodec codec,
			MessageHandler messageHandler) {
		this.jms = jms;
		this.connection = connection;
		this.codec = codec;
		this.messageHandler = messageHandler;
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

	private MessageHandler getMessageHandler() {
		return messageHandler;
	}

	public void start() {
		startConnection();
		startConsumer();
	}
	
	private void startConnection() {
		try {
			getConnection().start();
		} catch (JMSException e) {
			log.error("Failed to start jms connection");
			throw new RuntimeException(e);
		}
	}
	
	private void startConsumer() {
		try {
			Session session = createSession();
			MessageConsumer consumer = createConsumer(session);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			log.error("Failed to start message consumer");
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onMessage(Message message) {
		getMessageHandler().onMessage(message);
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
		try {
			Session session = createSession();
			Message message = getCodec().createMessage(session, event);
			createProducer(session).send(message);
		} catch (Exception e) {
			log.error(String.format("Failed to send message for event %s", event), e);
		}
	}
	
	private Session createSession() throws JMSException {
		return getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	private Destination createTopic(Session session) throws JMSException {
		return createSession().createTopic(jms().topic());
	}
	
	private MessageProducer createProducer(Session session) throws JMSException {
		return session.createProducer(createTopic(session));
	}

	private MessageConsumer createConsumer(Session session) throws JMSException {
		return session.createConsumer(createTopic(session));
	}
	
}
