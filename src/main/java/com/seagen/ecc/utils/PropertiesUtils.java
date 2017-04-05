package com.seagen.ecc.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对属性文件操作的工具类 获取，新增，修改 注意： 以下方法读取属性文件会缓存问题,在修改属性文件时，不起作用， 　InputStream in =
 * PropertiesUtils.class.getResourceAsStream("/config.properties"); 　解决办法：
 * 　String savePath =
 * PropertiesUtils.class.getResource("/config.properties").getPath();
 * 
 * @author chenjinlong 2013-11-19
 * @Email: chenjinlong_sea@163.com
 * @version 1.0v
 */
public class PropertiesUtils {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

	/**
	 * 获取属性文件的数据 根据key获取值
	 * 
	 * @param fileName
	 *            文件名　(注意：加载的是src下的文件,如果在某个包下．请把包名加上)
	 * @param key
	 * @return
	 */
	public static String findPropertiesKey(String key) {

		try {
			Properties prop = getProperties();
			return prop.getProperty(key);
		} catch (Exception e) {
			return "";
		}

	}

	public static void main(String[] args) {
		Properties prop = new Properties();
		InputStream in = PropertiesUtils.class
				.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
			Iterator<Entry<Object, Object>> itr = prop.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<Object, Object> e = (Entry<Object, Object>) itr.next();
				System.err.println((e.getKey().toString() + "" + e.getValue()
						.toString()));
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 返回　Properties
	 * 
	 * @param fileName
	 *            文件名　(注意：加载的是src下的文件,如果在某个包下．请把包名加上)
	 * @param
	 * @return
	 */
	public static Properties getProperties() {
		Properties prop = new Properties();
		String savePath = PropertiesUtils.class.getResource(
				"/config.properties").getPath();
		// 以下方法读取属性文件会缓存问题
		// InputStream in = PropertiesUtils.class
		// .getResourceAsStream("/config.properties");
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					savePath));
			prop.load(in);
		} catch (Exception e) {
			return null;
		}
		return prop;
	}

	/**
	 * 写入properties信息
	 * 
	 * @param key
	 *            名称
	 * @param value
	 *            值
	 */
	public static void modifyProperties(String key, String value) {
		try {
			// 从输入流中读取属性列表（键和元素对）
			Properties prop = getProperties();
			prop.setProperty(key, value);
			String path = PropertiesUtils.class.getResource(
					"/config.properties").getPath();
			FileOutputStream outputFile = new FileOutputStream(path);
			prop.store(outputFile, "modify");
			outputFile.close();
			outputFile.flush();
		} catch (Exception e) {
		}
	}

	/**
	 * 将指定路径的properties资源文件文件中的属性注入的指定类型的类中<br>
	 * 用法如: person.properties:<br>
	 * person.name=John<br>
	 * person.age=3<br>
	 * <code>
	 * Person person=readProperties("person.properties",Person.class,"person.");
	 * </code>
	 * 
	 * @param path
	 *            资源文件路径
	 * @param cla
	 *            要转换的类型
	 * @param prefix
	 *            属性前缀
	 * @return 当转换失败是返回null
	 */
	public static <T> T readProperties(String path, Class<T> cla, String prefix) {
		log.debug("readProperties:" + path + "->" + cla.getName());
		try {
			T ret = cla.newInstance();
			InputStream is = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream(path);
			Properties prop = new Properties();
			try {
				prop.load(is);
			} catch (Exception e1) {
				if (is != null) {
					is.close();
				}
				is = new FileInputStream(path);
				prop.load(is);
			}
			is.close();
			BeanInfo bi = Introspector.getBeanInfo(cla);
			PropertyDescriptor[] pd = bi.getPropertyDescriptors();
			Pattern p = Pattern.compile("\\$\\{\\S+\\}");
			for (int i = 0; i < pd.length; i++) {
				String name = pd[i].getName();
				String value = prop.getProperty(prefix + name);
				if (value != null && p.matcher(value).matches()) {
					value = value.substring(2, value.length() - 1);
					value = prop.getProperty(value);
				}
				if (value == null) {
					continue;
				}
				Method method = pd[i].getWriteMethod();
				try {
					Object retVal = getTypeVal(value,
							method.getParameterTypes()[0]);
					method.invoke(ret, retVal);
					log.debug("set " + name + "=" + retVal);
				} catch (Exception e) {
					log.warn("getTypeVal error", e);
				}
			}
			return ret;
		} catch (Exception e) {
			log.error("readProperties error", e);
			return null;
		}
	}

	/**
	 * 将字符串转换为自定的类型(只支持简单的类型,如String,long,int,short,boolean)
	 * 
	 * @param obj
	 *            要转换的字符串
	 * @param type
	 *            要转换成的类型
	 * @return 当类型不支持时,可能发送异常
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getTypeVal(String obj, Class<T> type) {
		obj = obj.trim();
		String typeName = type.getSimpleName();
		if (typeName.equals("String")) {
			return (T) obj;
		} else if (typeName.equalsIgnoreCase("long")) {
			return (T) new Long(obj);
		} else if (typeName.equals("int") || typeName.equals("Integer")) {
			return (T) new Integer(obj);
		} else if (typeName.equalsIgnoreCase("short")) {
			return (T) new Short(obj);
		} else if (typeName.equalsIgnoreCase("boolean")) {
			return (T) new Boolean(obj);
		}
		return (T) obj;
	}

	public static Properties loadProperties(File file) {
		try {
			Properties ret = new Properties();
			InputStream is = new FileInputStream(file);
			ret.load(is);
			return ret;
		} catch (Exception e) {
			log.error("loadProperties failed", e);
		}
		return null;
	}

	/**
	 * 根据字段名称设置值
	 * 
	 * @param map
	 * @param bean
	 * @return
	 */
	public static void setValByField(Map<String, String> map, Object bean) {
		Class<?> cla = bean.getClass();
		Field[] fields = cla.getFields();
		for (Field field : fields) {
			String name = field.getName();
			String value = map.get(name);
			if (value != null && !"".equals(value.trim())) {
				value = value.trim();
				Object val = getTypeVal(value, field.getType());
				try {
					field.set(bean, val);
					log.info("setValByField ok:" + name + "=" + value);
				} catch (Exception e) {
					log.info("setValByField failed:" + name + "=" + value);
				}
			}
		}
	}

	public static Map<String, String> bean2mapStr(Object op) {
		Map<String, String> values = new HashMap<>();
		BeanInfo bi = null;
		try {
			bi = Introspector.getBeanInfo(op.getClass());
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
				try {
					Object data = pd.getReadMethod().invoke(op);
					if (data instanceof Date) {
						data = DateUtils.datetimeToString((Date) data);
					}
					values.put(pd.getName(), data==null?null:data.toString());
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return values;
	}

	public static Map<String, Object> bean2map(Object op) {
		Map<String, Object> values = new HashMap<>();
		BeanInfo bi = null;
		try {
			bi = Introspector.getBeanInfo(op.getClass());
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
				try {
					Object data = pd.getReadMethod().invoke(op);
					values.put(pd.getName(), data);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return values;
	}
}
