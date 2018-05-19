package com.kamino.clone.jms.message;

import javax.jms.Message;

import com.kamino.clone.jms.event.Event;

public interface MessageCodec {

	public Message createMessage(Event event);
	
}
