package com.kamino.go.jms.message;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.google.inject.Inject;
import com.kamino.go.jms.event.Event;
import com.kamino.go.json.JSONMapper;

public class JSONMessageCodec implements MessageCodec {
	private JSONMapper mapper;
	
	@Inject
	public JSONMessageCodec(JSONMapper mapper) {
		this.mapper = mapper;
	}

	private JSONMapper getMapper() {
		return mapper;
	}

	@Override
	public Message createMessage(Session session, Event event) throws Exception {
		TextMessage message = session.createTextMessage();
		message.setText(getMapper().map(event.getMessageBody()));
		return message;
	}

}
