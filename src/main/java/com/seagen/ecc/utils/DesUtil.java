package com.seagen.ecc.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * 
*  深圳云柜科技社区通项目
* @Description: DES算法工具
* @author 唐经纬<qq254080862@163.com>  
* @date 2014年11月18日 下午2:23:00 
* @version V1.0
 */
public class DesUtil {
	private static String Algorithm = "DES";
	private static String key = "CB7A92E3";
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static String encryptDES(String encryptString, String encryptKey)
			throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), Algorithm);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
		return Base64.encode(encryptedData);
	}

	public static String decryptDES(String decryptString, String decryptKey)
			throws Exception {
		byte[] byteMi = Base64.decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), Algorithm);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);

		return new String(decryptedData, "UTF-8");
	}

	public static String encryptData2(String data) throws Exception {
		return encryptDES(data, key);
	}

	public static String decryptData2(String data) throws Exception {
		return decryptDES(data, key);
	}

	public static void main(String[] args) {
		try {
			String string = "{\"functionCode\":\"303\",\"paramList\":{\"phoneCode\":\"185666299379\",\"pwd\":\"123456\"}}";
			System.err.println(encryptData2(string));
			System.err.println(decryptData2(encryptData2(string)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
