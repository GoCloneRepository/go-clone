package com.kamino.go.server;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.*;
import com.kamino.go.server.shutdown.ShutdownTask;
import com.kamino.go.server.startup.StartupTask;

public abstract class GuiceServer {
	private static final Logger log = LoggerFactory.getLogger(GuiceServer.class);
	
	private Injector injector;

	public abstract Module[] getModules();
	
	private Injector getInjector() {
		if(injector == null) {
			injector = Guice.createInjector(getModules());
		}
		return injector;
	}
	
	private <T> T get(Class<T> cl) {
		return getInjector().getInstance(cl);
	}
	
	public void run() {
		try {
			executeStartupTasks();
			Thread.currentThread().join();
		}
		catch(Exception e) {
			log.error("Exception occured during startup.", e);
		}
		finally {
			executeShutdownTasks();
		}
	}
	
	private void executeStartupTasks() {
		Stream.of(get(StartupTask[].class))
			  .forEach(StartupTask::execute);
		log.info("Finished executing startup tasks.");
	}
	
	private void executeShutdownTasks() {
		Stream.of(get(ShutdownTask[].class))
		  	  .forEach(ShutdownTask::execute);
		log.info("Finished executing shutdown tasks.");
	}
}
