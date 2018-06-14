package com.kamino.go.test;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {
	
	@GET
	public TestObject test() {
		return new TestObject("benji", 0);
	}
	
	public class TestObject {
		private String name;
		private int amount;
		
		public TestObject() {}
		
		public TestObject(String name, int amount) {
			this.name = name;
			this.amount = amount;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		
		
	}
}