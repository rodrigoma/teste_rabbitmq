package com.rodrigoma.business.startup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextFactory {

	private ApplicationContext context;

	private static ContextFactory instance;

	private ContextFactory() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		}
	}

	private ContextFactory(ApplicationContext context) {
		this.context = context;
	}

	public static void init() {
		instance = new ContextFactory();
	}

	public static void initWeb(ApplicationContext context) {
		instance = new ContextFactory(context);
	}

	public static ContextFactory getInstance() {
		if ( instance == null ) {
			init();
		}
		return instance;
	}

	public Object getBean(String bean) {
		return context.getBean(bean);
	}

	public ApplicationContext getApplicationContext() {
		return context;
	}
}
