package com.seagen.ecc.utils.httpClient;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.DesUtil;
import com.seagen.ecc.utils.JsonUtil;
/**
 * 
*  深圳云柜科技社区通项目
* @Description: http客户端应用工具 
* @author 唐经纬<qq254080862@163.com>  
* @date 2014年11月18日 下午2:23:00 
* @version V1.0
 */
public class HttpClientUitl {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUitl.class
			.getName());

	/**
	 * 封装对象
	 * 
	 * @param obj
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	public static String encapsulation(HttpResponseBean responseBean)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		String jsonData = "";

		if (logger.isDebugEnabled()) {
			logger.debug("responseBean " + responseBean);

		}
		jsonData = JsonUtil.ojbToJsonStr(responseBean);
		if (logger.isDebugEnabled()) {
			logger.debug("after object to json string " + jsonData);
		}
		try {
			jsonData = DesUtil.encryptData2(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("after encrypt " + jsonData);
		}
		return jsonData;
	}

	/**
	 * 解析对象
	 * 
	 * @param returns
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static HttpRequestBean analyze(String jsonData)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException{
		HttpRequestBean bean = null;
		if (logger.isDebugEnabled()) {
			logger.debug("jsonData " + jsonData);
		}
		try {
			jsonData = DesUtil.decryptData2(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("after decrypt " + jsonData);
		}
		bean = JsonUtil.jsonStrToObj(jsonData, HttpRequestBean.class);
		if (logger.isDebugEnabled()) {
			logger.debug("after json to object " + bean);
		}
		return bean;
	}

	

	
}
