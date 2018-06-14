package com.kamino.go.test.settings;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kamino.go.jms.settings.Jms;
import com.kamino.go.settings.*;

@RunWith(MockitoJUnitRunner.class)
public class TestSettingsImpl {
	@Mock private SettingsProvider provider;
	
	@InjectMocks
	private SettingsImpl settings;

	private Rest rest() {
		return settings.rest();
	}
	
	private Jms jms() {
		return settings.jms();
	}
	
	@Test
	public void restPort_fromProvider() {
		rest().port();
		
		verify(provider).get("REST_PORT");
	}
	
	@Test
	public void restPort_default8080() {
		int port = rest().port();
		
		assertThat(port, is(equalTo(8080)));
	}
	
	@Test
	public void jmsTopic_fromProvider() {
		jms().topic();
		
		verify(provider).get("JMS_TOPIC");
	}
	
	@Test
	public void jmsTopic_defaultTopic() {
		String topic = jms().topic();
		
		assertThat(topic, is(equalTo("topic")));
	}
	
	@Test
	public void jmsPort_fromProvider() {
		jms().port();
		
		verify(provider).get("JMS_PORT");
	}
	
	@Test
	public void jmsPort_default61616() {
		int port = jms().port();
		
		assertThat(port, is(equalTo(61616)));
	}
	
	@Test
	public void jmsHost_fromProvider() {
		jms().host();
		
		verify(provider).get("JMS_HOST");
	}
	
	@Test
	public void jmsHost_defaultLocalhost() {
		String host = jms().host();
		
		assertThat(host, is(equalTo("localhost")));
	}

}
