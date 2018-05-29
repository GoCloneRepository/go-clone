package com.kamino.go.test;

import org.junit.Test;

import com.kamino.go.RestServer;
import com.kamino.go.RestServerBuilder;
import com.kamino.go.resources.ResourceProvider;
import com.kamino.go.settings.Rest;

public class TestRestServerBuilder {

	@Test
	public void build_createsRestServer() throws Exception {
		RestServer server = new RestServerBuilder()
				.withRestSettings(new TestRest())
				.withResourceProvider(new TestResourceProvider())
				.build();
		
		try {
			server.start();
			server.join();
		}
		finally {
			server.destroy();
		}
	}
	
	private class TestRest implements Rest {

		@Override
		public int port() {
			return 8080;
		}
		
	}
	
	private class TestResourceProvider implements ResourceProvider {

		@Override
		public Object[] get() {
			return new Object[] {
				new TestResource()
			};
		}
		
	}

}
