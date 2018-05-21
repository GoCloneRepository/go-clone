package com.kamino.go.jms.message;

import javax.jms.Message;
import javax.jms.Session;

import com.kamino.go.jms.event.Event;

public interface MessageCodec {

	public Message createMessage(Session session, Event event) throws Exception;
	
}
