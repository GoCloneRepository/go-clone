package com.kamino.go.test.jms;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.clone.jms.event.Event;
import com.kamino.clone.jms.message.MessageCodec;
import com.kamino.clone.jms.settings.Jms;
import com.kamino.go.jms.JmsService;

@RunWith(MockitoJUnitRunner.class)
public class TestJmsService {
	@Mock private Jms jms;
	@Mock private Connection connection;
	@Mock private MessageCodec codec;

	@Mock private Session session;
	@Mock private Topic topic;
	@Mock private MessageProducer producer;

	@InjectMocks
	private JmsService service;
	
	@Before
	public void setup() throws JMSException {
		when(connection.createSession(anyBoolean(), anyInt())).thenReturn(session);
		when(session.createTopic(any())).thenReturn(topic);
		when(session.createProducer(any())).thenReturn(producer);
	}
	
	private JmsService getService() {
		return service;
	}
	
	@Test
	public void start_startsConnection() throws JMSException {
		getService().start();
		
		verify(connection).start();
	}
	
	@Test
	public void stop_stopsConnection() throws JMSException {
		getService().stop();
		
		verify(connection).close();
	}
	
	@Test
	public void post_createsMessageFromCodec() {
		Event event = mock(Event.class);
		
		getService().post(event);
		
		verify(codec).createMessage(event);
	}
	
	@Test
	public void post_sendsMessageWithProducer() throws JMSException {
		Message message = mock(Message.class);
		when(codec.createMessage(any())).thenReturn(message);
		
		getService().post(mock(Event.class));
		
		verify(producer).send(message);
	}
}
