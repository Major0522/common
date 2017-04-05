package com.seagen.ecc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Spring处理助手，通过这个助手可以获取到Spring的Bean对象。
 * 
 * @author wutianbin
 *
 */
public class SpringHelpper {
	
	private static Logger log = LoggerFactory.getLogger(SpringHelpper.class);

	private static ApplicationContext beanContext = null;

	private static ApplicationContext getContextBeanFactory() {

		if (beanContext == null) {
			beanContext = ApplicationHelper.getApplicationContext();
		}
		
		return beanContext;
	}

	public static Object getBean(String name) {
		Object obj = null;
		try {
			obj = getContextBeanFactory().getBean(name);
		} catch (Exception e) {
			log.error("getBean() failed", e);
		}
		return obj;
	}

}
