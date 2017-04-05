package com.seagen.ecc.common;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring的应用程序上下文处理助手。
 * 
 * @author wutianbin
 * 
 */
@Component
public class ApplicationHelper implements ApplicationContextAware {

	private static ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appContext = applicationContext;
	}

	/**
	 * 获取应用程序的上下文对象。
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return appContext;
	}

	public static Object getBean(String name) {
		return appContext.getBean(name);
	}

	public static <T> T getBean(Class<T> cla) {
		return appContext.getBean(cla);
	}

	/**
	 * 当存在多个时,取第一个,没有时返回null
	 */
	public static <T> T getFirstBean(Class<T> cla) {
		Map<String, T> map = appContext.getBeansOfType(cla);
		if (map.size() >= 0) {
			for (T t : map.values()) {
				return t;
			}
		}
		return null;
	}

}
