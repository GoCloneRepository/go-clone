package com.kamino.go;

import com.google.inject.Module;
import com.kamino.go.modules.*;
import com.kamino.go.server.GuiceServer;

public class Server extends GuiceServer {

	public static void main(String[] args) {
		Server server = new Server();
		server.run();
	}

	@Override
	public Module[] getModules() {
		return new Module[] {
			new JmsServiceModule(),
			new RestModule(),
			new SettingsModule(),
			new ServerModule()
		};
	}
	
}
