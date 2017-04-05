package com.seagen.ecc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5算法
 * 
 * @version 1.0 2010-10-12
 * @author 吴天斌
 */
public class MD5Utils {

	public final static String fileMD5Ex(String fileName) {
		File file = new File(fileName);
		return fileMD5(file);
	}
	public final static String fileMD5(String fileName) {
		File file = new File(fileName);
		return fileMD5(file);
	}

	/**
	 * MD5加密算法
	 * 
	 * @param sourceStr
	 *            要加密的明文
	 * @return 加密后的密文
	 */
	public final static String stringMD5(String sourceStr) {
		byte[] btInput = sourceStr.getBytes();
		return byteMD5(btInput);
	}

	public final static String stringMD5(String sourceStr, Charset charset) {
		byte[] btInput = sourceStr.getBytes(charset);
		return byteMD5(btInput);
	}

	public final static String fileMD5(File file) {
		FileInputStream in = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			byte buffer[] = new byte[1024];
			int len;
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			return byte2hex(digest.digest());
		} catch (Exception e) {
			log.error("fileMD5Ex error ", e);
			return "";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String byte2hex(byte[] buf) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			int hi = buf[i] >> 4 & 0x0f;
			int lo = buf[i] & 0x0f;
			sBuffer.append((char) (hi > 9 ? (hi - 10 + 'A') : (hi + '0')));
			sBuffer.append((char) (lo > 9 ? (lo - 10 + 'A') : (lo + '0')));
		}
		return sBuffer.toString();
	}

	private final static String byteMD5(byte[] btInput) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			byte byte0;

			for (int i = 0; i < j; i++) {
				byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}

			return new String(str);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	public static String byteMD5(ByteBuffer btInput) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			byte byte0;

			for (int i = 0; i < j; i++) {
				byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}

			return new String(str);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	public static void main(String[] args) {
		String md5 = MD5Utils.fileMD5("C:\\temp\\ectbs/ectbs.jar");
		System.out.println(md5);
	}

	private static Logger log = LoggerFactory.getLogger(MD5Utils.class);
}
