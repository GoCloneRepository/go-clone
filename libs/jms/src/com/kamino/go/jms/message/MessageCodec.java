package com.kamino.go.jms.message;

import javax.jms.Message;

import com.kamino.go.jms.event.Event;

public interface MessageCodec {

	public Message createMessage(Event event);
	
}
