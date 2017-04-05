package com.seagen.ecc.common.locale;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.common.options.CommonOptions;
import com.seagen.ecc.utils.ExceptionUtils;

/**
 * 本地信息加载器，从配置文件中读出有关Locale的配置信息.
 * 
 * @version	1.0 2010-10-18 
 * @author	吴天斌
 */
public class LocaleLoader {
	private final static Logger log = LoggerFactory.getLogger(LocaleLoader.class);
	
	private static Locale locale;
	
	/**
	 * 是否已经加载配置信息的标志。
	 */
	private static Boolean loaded = false;
	
	/**
	 * 获取Locale实例。
	 * 
	 * @return 当前配置的Locale实例。
	 */
	public static Locale getLocale() {
		if (!loaded)
		{
			try {
                loadLocaleFromConfig();
            } catch (FileNotFoundException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            } catch (IOException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
		}
		
		return locale;
	}	
	
	/**
	 * 从配置文件中加载Locale信息，并生成Locale实例。
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static void loadLocaleFromConfig() throws FileNotFoundException, IOException {
		// 从配置文件中读出Locale的配置信息
		CommonOptions options = CommonOptions.getOptions();
		
		String lang = "zh";
		String country = "CN";
		
		if (options != null)
		{	
			lang = options.getLocale().getLanguage();
			country = options.getLocale().getCountry();
		}	
		
		if ((lang == null) || (lang.equals(""))) {
			lang = "zh";
		}
		
		if ((country == null) || (country.equals(""))) {
			country = "CN";
		}
		
		// 根据配置信息设置locale
		locale = new Locale(lang, country);
		
		loaded = true;
	}
	
}
