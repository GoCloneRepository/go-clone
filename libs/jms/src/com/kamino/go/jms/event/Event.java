package com.kamino.go.jms.event;

import com.kamino.go.jms.message.MessageBody;

public interface Event {

	public MessageBody getMessageBody();
	
}
