package com.kamino.go.test.rest.event;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.container.ContainerRequestContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.event.RequestEvent;
import com.kamino.go.jms.event.RequestMessageBody;
import com.kamino.go.jms.message.MessageBody;
import com.kamino.go.rest.filter.RequestName;

@RunWith(MockitoJUnitRunner.class)
public class TestRequestEvent {
	@Mock private RequestName requestName;
	@Mock private ContainerRequestContext requestContext;
	
	@InjectMocks
	private RequestEvent event;
	
	private RequestEvent getEvent() {
		return event;
	}

	@Test
	public void getMessageBody_isRequestMessageBody() {
		MessageBody body = getEvent().getMessageBody();
		
		assertThat(body, is(instanceOf(RequestMessageBody.class)));
	}
}
