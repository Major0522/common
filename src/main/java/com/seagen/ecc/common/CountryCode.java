package com.seagen.ecc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.common.options.CommonOptions;
import com.seagen.ecc.utils.ExceptionUtils;

/**
 * 电话号码中的国家代码，在配置文件中设置。
 * 
 * @author wutianbin
 *
 */
public class CountryCode {

	private static final String DEFAULT_CODE = "+86";
	
	/**
	 * 获取电话号码中的国家代码。
	 * 
	 * @return
	 */
	public static String getPhoneCode() {
	    try {
	        CommonOptions options = CommonOptions.getOptions();
		
	        if (options != null && options.getLocale() != null
	                && options.getLocale().getCountryCodePhone() != null)
	        {
	            return options.getLocale().getCountryCodePhone();
	        }
	    } catch(Exception e) {
	    	log.error(ExceptionUtils.getStackTrace(e));
	    }
	    
	    
        return DEFAULT_CODE;    
	}
	
	private final static Logger log = LoggerFactory.getLogger(CountryCode.class);
}
