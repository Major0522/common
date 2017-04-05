package com.seagen.ecc.common;

import java.util.Random;

/**
 * 
 * 验证码+时间来验证有效性，防止超时
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2013-12-26
 */
public class TimeCode {

	/**
	 * 检验验证码的有效性
	 * 
	 * @param actualCode
	 *            从用户处获取的
	 * @param expectedCode
	 *            系统中保存的带时间参数的
	 * @param timeout
	 *            超时时间
	 * @return 如果通过则为true，否则false
	 */
	public static boolean check(String actualCode, String expectedCode,
			int timeout) {
		if (expectedCode != null && expectedCode.length() > 8) {
			int time = Integer.parseInt(expectedCode.substring(0, 8));
			if (Integer.parseInt(getCurTimeNum()) - time > timeout) {// 过期
				return false;
			}
			String code = expectedCode.substring(8);
			if (code.equalsIgnoreCase(actualCode)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测短信验证码是否在一定时间类发送的，防止重复发送验证码
	 * 
	 * @param smsCode
	 *            带时间标签的短信验证码
	 * @param repeatTime
	 *            间隔时间
	 * @return 当该短信验证码发送时间在一定时间前的话返回true
	 */
	public static boolean checkSmsRepeat(String smsCode, int repeatTime) {
		if (smsCode != null && smsCode.length() > 8) {
			int time = Integer.parseInt(smsCode.substring(0, 8));
			if (Integer.parseInt(getCurTimeNum()) - time > repeatTime) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 给验证码加上时间标签
	 * 
	 * @param code
	 *            验证码
	 * @return
	 */
	public static String getTimeCode(String code) {
		return getCurTimeNum() + code;
	}

	/**
	 * 生成一个数字验证码
	 * 
	 * @param len
	 *            验证码长度
	 * @return
	 */
	public static String getRandomCode(int len) {
		Random random = new Random();
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < len; i++) {
			ret.append(random.nextInt(10));
		}
		return ret.toString();
	}

	/**
	 * 生成一个8位长的时间值，用于短期的时间校验
	 * 
	 * @return
	 */
	private static String getCurTimeNum() {
		return (System.currentTimeMillis() + "").substring(2, 10);
	}
}