package com.rodrigoma.business.startup;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class StartUp extends DispatcherServlet {

	private static final long serialVersionUID = 348342344241844500L;

	@Override
	protected WebApplicationContext findWebApplicationContext() {
		WebApplicationContext wc = createWebApplicationContext(null);

		System.out.println("[StartUp Begin] RabbitMQ Teste");
		
		ContextFactory.initWeb(wc);
		
		System.out.println("[StartUp End] RabbitMQ Teste");

		return wc;
	}
}