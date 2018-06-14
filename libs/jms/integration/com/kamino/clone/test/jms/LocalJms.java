package com.kamino.clone.test.jms;

import com.kamino.go.jms.settings.Jms;

public class LocalJms implements Jms {

	@Override
	public String topic() {
		return "topic";
	}

	@Override
	public String host() {
		return "localhost";
	}

	@Override
	public int port() {
		return 61616;
	}
	
}
