package com.seagen.ecc.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 用户账户生成和判断工具
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2013-12-26
 */
public class AccountTool {
	public static void main(String[] args) {
		System.out.println(isAccountNo("20010001"));
	}

	/**
	 * 判断是否手机号
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isTel(String key) {
		Pattern pattern = Pattern.compile("^(1[3|4|5|6|7|8][0-9])\\d{8}$");
		Matcher m = pattern.matcher(key);
		return m.matches();
	}

	/**
	 * 判断是否卡号
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isCardNo(String key) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher m = pattern.matcher(key);
		return m.matches();
	}

	/**
	 * 判断是否账户号
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isAccountNo(String key) {
		Pattern pattern = Pattern.compile("^\\d{8}$");
		Matcher m = pattern.matcher(key);
		return m.matches();
	}
}