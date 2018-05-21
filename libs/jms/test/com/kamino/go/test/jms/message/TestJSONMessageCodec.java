package com.kamino.go.test.jms.message;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.event.Event;
import com.kamino.go.jms.message.JSONMessageCodec;
import com.kamino.go.jms.message.MessageBody;
import com.kamino.go.json.JSONMapper;

@RunWith(MockitoJUnitRunner.class)
public class TestJSONMessageCodec {
	@Mock private JSONMapper mapper;

	@Mock private Session session;
	@Mock private Event event;
	@Mock private TextMessage message;
	@Mock private MessageBody body;
	
	@InjectMocks
	private JSONMessageCodec codec;
	
	@Before
	public void setup() throws JMSException {
		when(session.createTextMessage()).thenReturn(message);
		when(event.getMessageBody()).thenReturn(body);
	}
	
	private JSONMessageCodec getCodec() {
		return codec;
	}

	@Test
	public void createMessage_createsTextMessageFromSession() throws Exception {
		getCodec().createMessage(session, event);
		
		verify(session).createTextMessage();
	}
	
	@Test
	public void createMessage_mapsEventsMessageBody() throws Exception {
		getCodec().createMessage(session, event);
		
		verify(mapper).map(body);
	}
	
	@Test
	public void createMessage_setsMappedStringToMessage() throws Exception {
		when(mapper.map(any())).thenReturn("marieke stinkt");
		
		getCodec().createMessage(session, event);
		
		verify(message).setText("marieke stinkt");
	}
}
