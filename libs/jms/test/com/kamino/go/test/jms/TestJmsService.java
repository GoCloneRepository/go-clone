package com.kamino.go.test.jms;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.jms.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.JmsService;
import com.kamino.go.jms.event.Event;
import com.kamino.go.jms.message.MessageCodec;
import com.kamino.go.jms.message.MessageHandler;
import com.kamino.go.jms.settings.Jms;

@RunWith(MockitoJUnitRunner.class)
public class TestJmsService {
	@Mock private Jms jms;
	@Mock private Connection connection;
	@Mock private MessageCodec codec;
	@Mock private MessageHandler messageHandler;

	@Mock private Session session;
	@Mock private Topic topic;
	@Mock private MessageProducer producer;
	@Mock private MessageConsumer consumer;

	@InjectMocks
	private JmsService service;
	
	@Before
	public void setup() throws JMSException {
		when(connection.createSession(anyBoolean(), anyInt())).thenReturn(session);
		when(session.createTopic(any())).thenReturn(topic);
		when(session.createProducer(any())).thenReturn(producer);
		when(session.createConsumer(any())).thenReturn(consumer);
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
	public void start_createsConsumer_ifMessageHandlerSet() throws JMSException {
		getService().setMessageHandler(messageHandler);
		
		getService().start();
		
		verify(session).createConsumer(topic);
	}
	
	@Test
	public void start_doesNotCreateConsumer_ifNoMessageHandlerSet() throws JMSException {
		getService().start();
		
		verify(session, never()).createConsumer(topic);
	}
	
	@Test
	public void stop_stopsConnection() throws JMSException {
		getService().stop();
		
		verify(connection).close();
	}
	
	@Test
	public void post_createsMessageFromCodec() throws Exception {
		Event event = mock(Event.class);
		
		getService().post(event);
		
		verify(codec).createMessage(any(), eq(event));
	}
	
	@Test
	public void post_sendsMessageWithProducer() throws Exception {
		Message message = mock(Message.class);
		when(codec.createMessage(any(), any())).thenReturn(message);
		
		getService().post(mock(Event.class));
		
		verify(producer).send(message);
	}
}
