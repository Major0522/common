package com.seagen.ecc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数字相关的应用工具类
 * 
 * @author wutianbin
 *
 */
public class NumberUtils {

	private final static Logger logger = LoggerFactory
			.getLogger(StringUtils.class);

	/**
	 * 把字符串转化为Long，如果转化失败则返回null。
	 * 
	 * @param strValue 字符串表示的long数值，例如 “127980”。
	 * @return strValue对应的long数值，如果转化失败则返回null。
	 */
	public static Long parseLong(String strValue) {
		Long ret = null;
		
		try {
			if (StringUtils.isNotEmpty(strValue)) {
				ret = Long.parseLong(strValue.trim());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		return ret;
	}
	
	/**
	 * 把字符串转化为Integer，如果转化失败则返回null。
	 * 
	 * @param strValue 字符串表示的Integer数值，例如 “127980”。
	 * @return strValue对应的Integer数值，如果转化失败则返回null。
	 */
	public static Integer parseInt(String strValue) {
		Integer ret = null;
		
		try {
			if (StringUtils.isNotEmpty(strValue)) {
				ret = Integer.parseInt(strValue.trim());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		return ret;
	}

}
