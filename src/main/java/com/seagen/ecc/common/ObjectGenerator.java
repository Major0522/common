package com.seagen.ecc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.ExceptionUtils;

/**
 * 对象生成器，根据类名来生成对象.
 * 
 * @version	1.0 2010-11-15 
 * @author	吴天斌
 */
public class ObjectGenerator {
	
	/**
	 * 根据类名生成新的对象.
	 * 
	 * @param className    类名称，包含完整的路径.
	 * @param callingClass 调用的类.
	 * @return 对象实例，发生异常时返回 null.
	 */
	public static Object newObject(String className, Class<?> callingClass) 
	{
		try {
			Class<?> cls = loadClass(className, callingClass);
			
			if (cls != null) {	
				return cls.newInstance();
			}	
		} catch(ClassNotFoundException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		} catch(Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		} 
		
		return null;
	}
	
	private static Class<?> loadClass(String className, Class<?> callingClass) 
			throws ClassNotFoundException 
	{
		try {
			return Thread.currentThread().getContextClassLoader()
					.loadClass(className);
		} catch(ClassNotFoundException e) {
			try {
				return Class.forName(className);
			} catch(ClassNotFoundException ex) {
				try {
					return ObjectGenerator.class.getClassLoader()
							.loadClass(className);
				} catch(ClassNotFoundException exc) {
					if (callingClass == null) {
						return null; 
					} else {
						return callingClass.getClassLoader()
								.loadClass(className);
					}	
				}
			}
		}
	}
	
	private static Logger log = LoggerFactory.getLogger(ObjectGenerator.class);
}
