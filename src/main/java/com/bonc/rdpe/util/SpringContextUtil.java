package com.bonc.rdpe.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

	// Spring应用上下文环境
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext context) {
		SpringContextUtil.applicationContext = context;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}
