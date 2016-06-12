package com.dikaros.wowserver.websocket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CommandConetextListener implements ServletContextListener{
	public static boolean flag = true;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Commander commander = new Commander();
		commander.start();
	}
	
}
