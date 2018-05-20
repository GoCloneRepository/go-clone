package com.kamino.go.jms.message;

import javax.jms.Message;

public interface MessageHandler {

	public void onMessage(Message message);
	
}
