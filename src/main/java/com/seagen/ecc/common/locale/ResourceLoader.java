package com.seagen.ecc.common.locale;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.ExceptionUtils;

/**
 * 资源信息加载器，处理系统的提示或异常信息字符串，从相关的properties文件中加载信息。
 * 
 * @version	1.0 2010-10-18 
 * @author	吴天斌
 */
public class ResourceLoader {

	private static Logger log = LoggerFactory.getLogger(ResourceLoader.class);
	
	private static final String undefineRes = "undefine value (key: %s)";
	
	private ResourceBundle res = null;

	/**
	 * 以配置文件名称为参数的构造器。
	 * 
	 * @param fileName 配置文件名称。
	 */
	public ResourceLoader(String fileName) {
		Locale lc = LocaleLoader.getLocale();
		 
		try {
			res = ResourceBundle.getBundle(fileName, lc);
		} catch(MissingResourceException ex) {
			log.error(ExceptionUtils.getStackTrace(ex));
		}	
	}
	
	/**
	 * 根据（键值，参数列表）获取资源信息串。
	 * 
	 * @param key 键值。
	 * @param params 参数列表。
	 * @return 对应的资源信息串。
	 */
	public String getString(String key, Object ... args) {
		if (null == res) {
			return "";
		}
		try {
			String outString = res.getString(key);
			return String.format(outString, args);
		} catch(Exception ex) {
			log.error(ExceptionUtils.getStackTrace(ex));

			//return null;
			return String.format(undefineRes, key);
		}		
	}
    
}
