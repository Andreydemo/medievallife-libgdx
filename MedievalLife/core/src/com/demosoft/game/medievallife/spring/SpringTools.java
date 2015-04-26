package com.demosoft.game.medievallife.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTools {
	private static ApplicationContext context;

	public static void initContext() {
		context = new ClassPathXmlApplicationContext("appContext.xml");
	}

	public static Object getBean(String name) throws BeansException {
		return context.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return context.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return context.getBean(requiredType);
	}

	public static Object getBean(String name, Object... args) throws BeansException {
		return context.getBean(name, args);
	}

	public static <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
		return context.getBean(requiredType, args);
	}
}
