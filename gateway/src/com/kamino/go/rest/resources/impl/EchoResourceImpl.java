package com.kamino.go.rest.resources.impl;

import com.kamino.go.rest.filter.RequestName;
import com.kamino.go.rest.resources.EchoResource;

public class EchoResourceImpl implements EchoResource {

	@Override
	@RequestName(REQUEST_ECHO)
	public String echo(String text) {
		return text;
	}

}
