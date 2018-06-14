package com.kamino.go.jms.event;

import javax.ws.rs.container.ContainerRequestContext;

import com.kamino.go.jms.message.MessageBody;
import com.kamino.go.rest.filter.RequestName;

public class RequestEvent implements Event {
	private RequestName requestName;
	private ContainerRequestContext requestContext;
	
	public RequestEvent(
			RequestName requestName, 
			ContainerRequestContext requestContext) {
		this.requestName = requestName;
		this.requestContext = requestContext;
	}

	private RequestName getRequestName() {
		return requestName;
	}

	private ContainerRequestContext getRequestContext() {
		return requestContext;
	}

	@Override
	public MessageBody getMessageBody() {
		return new RequestMessageBody(getRequestName(), getRequestContext());
	}

	@Override
	public String toString() {
		MessageBody body = getMessageBody();
		return String.format("RequestEvent: %s - %s", body.getName(), body.getBody());
	}
}
